# BungeeChatChannels

A custom developed chat channel plugin with customizable options for BungeeCord networks. Features 5 channels, all with separate permissions (Owner, Developer, Admin, Moderator/Staff, and Network wide), separate channel logging by day, and optional console usage. Message prefixes can be customized completely to fit any needs.



# Configuration

Here is the configuration file as it is initially loaded on first run (with added comments for understanding):
```
#Toggleable Section
toggles:
  allowConsole: false     #This option allows the console to interact with players via the channels
  sendDisabledMessages: false     #This option will send a message saying the channel is disabled
  logMessages     #This option toggles the logging of all messages

#This part is for each individual channel, which may be enabled/disabled separate of the other channels
  owner: true
  developer: true
  admin: true
  moderator: true
  network: true

#Prefix Section

#This section allows you to completely customize how the message looks when it is sent to the players.
prefix:
  owner: '&r[&cOwner Chat&r] &c{author}&r: &c{message}'
  developer: '&r[&aDeveloper Chat&r] &a{author}&r: &a{message}'
  admin: '&r[&5Admin Chat&r] &5{author}&r: &5{message}'
  moderator: '&r[&6Moderator Chat&r] &6{author}&r: &6{message}'
  network: '&r[&7Network&r] &7{author}&r: &7{message}'
```

# Commands
The most common aliases are used for each command, from full aliases (e.g. "developerchat"), to short alisases (e.g. "dc"). The settings command, which can toggle options on the fly while the network is running, only has two aliases, "chatchannelsettings", and "ccsettings".