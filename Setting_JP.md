# Modの設定
## 機械の設定
分解と複製、スキャンの所要時間が短すぎ / 長すぎですか？<br>
分解と複製、スキャンの要求電圧が低すぎ / 高すぎですか？<br>
スクラップ、マター増幅剤の入手確率が低すぎですか？ <br>

これらはconfigで変更できます.<br>
configフォルダのmaterialreplication.cfgを見てください.

## materialの設定
### materialごとの分解/複製の設定
デフォルトでは、化学式を持たないmaterialには分解機/複製機のレシピが生成されません.<br>
そのようなmaterialにレシピを登録したい場合、以下を用いてください.<br>
- *WhitelistMaterial* : 化学式を持たないmaterialにレシピを生成させます
    - default : glowstone

化学式を持つmaterialの分解機/複製機のレシピを削除したい場合, 以下を用いてください.<br>
- *blacklistForDeconstruction* : ここに書かれたmaterialは分解機のレシピが**生成されなくなります**.
    - default : none
- *blacklistForReplication* : ここに書かれたmaterialは複製機のレシピが**生成されなくなります**.
    - default :  granite, andesite, diorite, clay, brick, obsidian, flint, ice, charcoal, gunpowder, sugar, granite_black,
      granite_red, marble, basalt, quicklime, ash, concrete, dark_ash, water, distilled_water, steam
- *blacklistForMatter* : ここに書かれたmaterialは分解機/複製機のレシピが**生成されなくなります**.
    - default : none
CrTを用いてmaterialに特定のFlagをつけることで分解または複製を無効化できます.
- **no_deconstruction** > このFlagを持つmaterialは分解できません.
- **no_replication** > このFlagを持つmaterialは複製できません.

### 分解または複製の追加
CrTを用いてmaterialに化学式を付与することで分解/複製レシピが自動で登録されます.<br>
Materialsは**粉 (Dust)** または **液体 (Fluid)** がないといけません.

**Flagのつけ方または化学式のつけ方はどうやれば?**<br>
[GTCEu wiki](https://github.com/GregTechCEu/GregTech/wiki/CraftTweaker-for--Materials#modifying-existing-materials)をご覧ください.
