package com.authlyn.authlynbot.core;

import java.util.HashMap;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class Database {
	
	public static void main(String[] args) {
		
		testDatabase();
	}
	
	// RethinkDB Object
	public static final RethinkDB r = RethinkDB.r;
	
    // RethinkDB Connection
	static Connection conn = r.connection().connect();
	
	// Will install the database, tables, etc.
	public static void installDatabase() {
		
		try {
			
			r.dbCreate("authlyn").run(conn);
			
			r.db("authlyn").tableCreate("users").run(conn);
			r.db("authlyn").tableCreate("inventories").run(conn);
			r.db("authlyn").tableCreate("locations").run(conn);
			r.db("authlyn").tableCreate("npcs").run(conn);
			r.db("authlyn").tableCreate("mobs").run(conn);
			r.db("authlyn").tableCreate("items").run(conn);
			
			System.out.println("Success!");
			
		} catch (Exception e) {
			
			System.out.println("Whoops!\n" + e);
			
		}
	}
	
	// Just me testing and learning how to read and process the database
	public static void testDatabase() {
		
		r.db("authlyn").table("users").insert(r.hashMap("id", "122056184659312640")
				.with("health", 100)
				.with("gold", 1500)).run(conn);
		
		HashMap<String, ?> theUser = r.db("authlyn")
				.table("users")
				.get("122056184659312640").run(conn);
		
		// The user values
		String userId = theUser.get("id").toString();
		long userHealth = (long) theUser.get("health");
		long userGold = (long) theUser.get("gold");
		
		long healthPlusGold = userGold + userHealth;

		System.out.println(String.format("User ID: %s\nHealth: %s\nGold: %s\nHealth + Gold = %s",
				userId, userHealth, userGold, healthPlusGold));
	}
}
