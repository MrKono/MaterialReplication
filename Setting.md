# Mod Setting
## Machine Setting
Duration of Deconstructor / Replicator / Scan is too short/too long? <br>
Voltage of Deconstructor / Replicator / Scan is too Low/too High? <br>
Chance of getting Scrap / Matter Amplifier is too Low? <br>

These can be changed in configuration file.<br>
Please see materialreplication.cfg in your config folder.

## Material Setting
### Deconstructor/Replicator recipe setting for each material
By default, Deconstructor/Replicator recipe **will not be generated** for a material which does not have chemical formula.<br>
If you want to generate Deconstructor/Replicator for it, use the following.　
- *WhitelistMaterial* : Settings to generate recipes on material that does not have a chemical formula.
    - default : glowstone

If you want to delete a Deconstructor/Replicator recipe for a material with a chemical formula, you can set it from the cfg.<br>
- *blacklistForDeconstruction* : The material written here **does not** generate a recipe for Deconstructor.
  - default : none 
- *blacklistForReplication* : The material written here **does not** generate a recipe for Replicator.
  - default :  granite, andesite, diorite, clay, brick, obsidian, flint, ice, charcoal, gunpowder, sugar, granite_black,
    granite_red, marble, basalt, quicklime, ash, concrete, dark_ash, water, distilled_water, steam
- *blacklistForMatter* : The material written here **does not** generate a recipe for Deconstructor and Replicator.
  - default : none

### Add material for deconstruction or replication
By setting the internal chemical formula to a material using CrT, Deconstruction/Replication recipes are automatically registered.<br>
Materials must have **Dust** or **Fluid**

_How to set the the internal formula?_<br>
See [GTCEu wiki](https://github.com/GregTechCEu/GregTech/wiki/CraftTweaker-for--Materials#modifying-existing-materials)
