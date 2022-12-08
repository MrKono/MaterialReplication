# Mod Setting
## Machine Setting
Duration of Deconstructor / Replicator is too short/too long? <br>
Voltage of Deconstructor / Replicator is too Low/too High? <br>
Want to disable sample consumption in Replicator? <br>

These can be changed in configuration file.<br>
Please see materialreplication.cfg in your config folder.

## Material Setting
### Remove material for deconstruction or replication
It can be removed by adding a specific flag to the material using CrT.<br>
- **no_deconstruction** > Material with this flag cannot be processed by Deconstructor.
- **no_replication** > Material with this flag cannot be processed by Replicator.

### Add material for deconstruction or replication
By setting the internal formula to a material using CrT, Deconstruction/Replication recipes are automatically registered.<br>
Materials must have **Dust** or **Fluid**

_How to add the flags? How to set the the internal formula?_<br>
See [GTCEu wiki](https://github.com/GregTechCEu/GregTech/wiki/CraftTweaker-for--Materials#modifying-existing-materials)
