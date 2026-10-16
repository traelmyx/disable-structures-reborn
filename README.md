# Disable Structures Reborn

Port of [Disable Structures](https://github.com/Bennyboy1695/DisableStructures) by Bennyboy1695 to Minecraft 1.21.1 (NeoForge and Fabric).

Stops the structures you list from generating, vanilla or modded, and keeps `/locate` and explorer maps from pointing at them. Put ids in `config/disablestructures.json`, or `modid:*` to turn off every structure a mod adds.

Server side only: clients don't need it.

## Building

```
./gradlew buildAll       # all versions
./gradlew gameTestAll    # runs the game tests
```

## License

MIT, same as the original. See `LICENSE`.
