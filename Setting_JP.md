# Modの設定
## 機械の設定
**分解**と**複製**の所要時間は設定可能です.<br>
configフォルダのmaterialreplication.cfgを見てください.

## materialの設定
### 分解または複製の無効化
CrTを用いてmaterialに特定のFlagをつけることで分解または複製を無効化できます.
- **no_deconstruction** > このFlagを持つmaterialは分解できません.
- **no_replication** > このFlagを持つmaterialは複製できません.

### 分解または複製の追加
CrTを用いてmaterialに化学式を付与することで分解/複製レシピが自動で登録されます.<br>
Materialsは**粉 (Dust)** または **液体 (Fluid)** がないといけません.

**Flagのつけ方または化学式のつけ方はどうやれば?**<br>
[GTCEu wiki](https://github.com/GregTechCEu/GregTech/wiki/CraftTweaker-for--Materials#modifying-existing-materials)をご覧ください.
