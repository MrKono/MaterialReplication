# Modの設定
## 機械の設定
分解と複製の所要時間が短すぎ / 長すぎですか？<br>
分解と複製の要求電圧が低すぎ / 高すぎですか？<br>
複製機での見本消費を無効にしたいですか？<br>

これらはconfigで変更できます.<br>
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
