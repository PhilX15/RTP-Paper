Random Teleport PaperMC Plugin
===
Teleport to a random location in the world

## 📜 Features
- Teleport to a safe random location using a command
- Simple configuration of cooldown and teleportation range
- Permissions allowing detailed management of player privileges

## 🔨 Building
```
git clone https://github.com/PhilX15/RTP-Paper.git
cd RTP-Paper
./gradlew build
```
You can find the output in the build/libs directory.

## 📜 Commands
- **/rtp** - teleports to a random location within a specified range
- **/setrange <range>** - sets the range for random teleportation
- **/reload** - reloads the config.yml file

## 🔑 Permissions
- **rtp.rtp.use** - Grants access to /rtp command
- **rtp.rtp.bypasscooldown** - Bypasses the /rtp cooldown
- **rtp.setrange** - Grants access to /setrange <range> command
- **rtp.reload** - Grants access to /reload command
- **rtp.*** - Grants access to all plugin permissions
