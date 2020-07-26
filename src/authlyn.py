# Written by Damien Moon#2025
# Modified 2020... Super old ded code previously
# Hi Kandrina if you read this it's Davska's fault if something
# Doesn't work as intended, he disturbed me while I was rewriting this code
# To work with newer versions of discord.py and Python 3.8

import glob
import os
import json

from discord.ext import commands

# Initialize the bot, prefix and help description.
bot = commands.Bot(command_prefix='>', description='Pork pie')

@bot.event
async def on_ready():
    # Prints the bot name, discrim and ID when started up
    print(f'------\nReady!\n{bot.user}\n{bot.user.id}\n------')

# Searches for existing modules (commands) in /commands/
if __name__ == '__main__':
    for module in glob.glob('./commands/*.py'):
        print(module)
        try:
            bot.load_extension(f'commands.{os.path.basename(module)[:-3]}')
        except Exception as e:
            exc = f'{__name__}: {e}'
            print(f'Failed to load command {module}\n{exc}')

# Opens the json file in src directory and fetches the token
wdir = os.path.abspath(os.path.dirname(__file__)) # Fetches current working dir
with open(wdir + '/token.json') as f:
    token_file = json.load(f)
    token = token_file.get('token')
bot.run(token)