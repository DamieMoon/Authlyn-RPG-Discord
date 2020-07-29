package com.authlyn.authlynbot.core;

import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

import com.authlyn.authlynbot.Globals;
import com.rethinkdb.net.Cursor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Database {

	public static void main(String[] args) {
		//testDatabase();
		//installDatabase();
		//insert_items();
		//insert_locations();
		//insert_npcs();
		//insert_monsters();
		getTheNPC("Damien Moon");
	}
	
	// RethinkDB Object
	public static final RethinkDB r = RethinkDB.r;
	
    // RethinkDB Connection
	static Connection conn = r.connection().connect();
	
	// Will install the database, tables, etc.
	public static void installDatabase() {
		
		try {
			ArrayList<String> db_list = r.dbList().run(conn);
			//System.out.println(db_list.contains("authlyn"));

			if(db_list.contains("authlyn")){
				ArrayList<String> table_list = r.db("authlyn").tableList().run(conn);

				// Checking if all tables exist, if not, create them
				if(!table_list.contains("users")){
					r.db("authlyn").tableCreate("users").run(conn);
				}
				if(!table_list.contains("inventories")){
					r.db("authlyn").tableCreate("inventories").run(conn);
				}
				if(!table_list.contains("locations")){
					r.db("authlyn").tableCreate("locations").run(conn);
				}
				if(!table_list.contains("npcs")){
					r.db("authlyn").tableCreate("npcs").run(conn);
				}
				if(!table_list.contains("monsters")){
					r.db("authlyn").tableCreate("monsters").run(conn);
				}
				if(!table_list.contains("items")){
					r.db("authlyn").tableCreate("items").run(conn);
				}

			}else {
				r.dbCreate("authlyn").run(conn);

				r.db("authlyn").tableCreate("users").run(conn);
				r.db("authlyn").tableCreate("inventories").run(conn);
				r.db("authlyn").tableCreate("locations").run(conn);
				r.db("authlyn").tableCreate("npcs").run(conn);
				r.db("authlyn").tableCreate("monsters").run(conn);
				r.db("authlyn").tableCreate("items").run(conn);
			}

			System.out.println("Success!");
			
		} catch (Exception e) {
			
			System.out.println("Whoops!\n" + e);
			
		}
	}

	public static void insert_items(){
		r.db("authlyn").table("items").delete().run(conn);
		File items_directoryPath = new File(Globals.wdir + "/content/items");
		File item_Files_List[] = items_directoryPath.listFiles();
		for(File file : item_Files_List){
			JSONParser parser = new JSONParser();
			try{
				Object itemObj = parser.parse(new FileReader(file.getAbsolutePath()));
				JSONObject item = (JSONObject) itemObj;

				if(item.get("type").equals("consumable")) {
					//System.out.println("Consumable");
					r.db("authlyn").table("items").insert(r.hashMap("id", item.get("id"))
							.with("type", item.get("type"))
							.with("name", item.get("name"))
							.with("recovery", item.get("recovery"))
							.with("description", item.get("description"))
							.with("buyable", item.get("buyable"))
							.with("level", item.get("level"))
							.with("value", item.get("value"))).run(conn);
				}else if(item.get("type").equals("weapon")){
					//System.out.println("Weapon");
					r.db("authlyn").table("items").insert(r.hashMap("id", item.get("id"))
							.with("type", item.get("type"))
							.with("name", item.get("name"))
							.with("baseDamage", item.get("baseDamage"))
							.with("description", item.get("description"))
							.with("buyable", item.get("buyable"))
							.with("level", item.get("level"))
							.with("value", item.get("value"))).run(conn);
				}
			}catch (Exception e){
				System.out.println("Uh Oh");
			}
		}
	}

	public static void insert_locations(){
		r.db("authlyn").table("locations").delete().run(conn);
		File locations_directoryPath = new File(Globals.wdir + "/content/locations");
		File location_Files_List[] = locations_directoryPath.listFiles();
		for(File file : location_Files_List){
			JSONParser parser = new JSONParser();
			try{
				Object locObj = parser.parse(new FileReader(file.getAbsolutePath()));
				JSONObject loc = (JSONObject) locObj;

					r.db("authlyn").table("locations").insert(r.hashMap("id", loc.get("id"))
							.with("name", loc.get("name"))
							.with("level", loc.get("level"))
							.with("description", loc.get("description"))
							.with("npcs", loc.get("npcs"))
							.with("monsters", loc.get("monsters"))
							.with("resources", loc.get("resources"))
							.with("routes", loc.get("routes"))).run(conn);
			}catch (Exception e){
				System.out.println("Uh Oh");
			}
		}
	}

	public static void insert_npcs(){
		r.db("authlyn").table("npcs").delete().run(conn);
		File npcs_directoryPath = new File(Globals.wdir + "/content/npcs");
		File npc_Files_List[] = npcs_directoryPath.listFiles();
		for(File file : npc_Files_List){
			JSONParser parser = new JSONParser();
			try{
				Object npcObj = parser.parse(new FileReader(file.getAbsolutePath()));
				JSONObject npc = (JSONObject) npcObj;

				r.db("authlyn").table("npcs").insert(r.hashMap("id", npc.get("id"))
						.with("name", npc.get("name"))
						.with("race", npc.get("race"))
						.with("items", npc.get("items"))
						.with("lines", npc.get("lines"))).run(conn);
			}catch (Exception e){
				System.out.println("Uh Oh");
			}
		}
	}

	public static void insert_monsters(){
		r.db("authlyn").table("monsters").delete().run(conn);
		File monsters_directoryPath = new File(Globals.wdir + "/content/monsters");
		File monster_Files_List[] = monsters_directoryPath.listFiles();
		for(File file : monster_Files_List){
			JSONParser parser = new JSONParser();
			try{
				Object mobObj = parser.parse(new FileReader(file.getAbsolutePath()));
				JSONObject mob = (JSONObject) mobObj;

				r.db("authlyn").table("monsters").insert(r.hashMap("id", mob.get("id"))
						.with("name", mob.get("name"))
						.with("level", mob.get("level"))
						.with("base_attack", mob.get("base_attack"))
						.with("base_defense", mob.get("base_defense"))
						.with("hp", mob.get("hp"))
						.with("base_xp", mob.get("base_xp"))).run(conn);
			}catch (Exception e){
				System.out.println("Uh Oh");
			}
		}
	}

	// Fetches a specific NPC
	public static void getTheNPC(String npcName) {
		Cursor testCursor = r.db("authlyn").table("npcs").filter(r.hashMap("name", npcName)).run(conn);
		List iDontKnow = testCursor.toList();

		for (Object doc : testCursor) {
			System.out.println(doc);
		}
	}


}
