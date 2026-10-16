## 1.1.0

- Ported to Minecraft 1.21.1 for NeoForge and Fabric
- `modid:*` entries turn off every structure from a mod
- Disabling one structure no longer stops `/locate` from finding the others in the same group
- Nothing is disabled by default (the old default turned off desert pyramids)
- The config is now `config/disablestructures.json` and is re-read when a world loads
