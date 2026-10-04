# 随机串生成 / Random String

Android 随机字符串生成小工具。每次点击「生成」，从 `A-Z a-z 0-9`（62 个字符）中逐位独立均匀取样，
长度随机为 6、7、8 或 9。点「复制」写入系统剪贴板。**不记录任何生成过的字符串**（无持久化、无历史）。

纯 Java + Material 3（跟随系统深色、Android 12+ 动态取色）。

## 构建

```sh
./gradlew :app:assembleDebug          # 调试包
./gradlew :app:test                   # JVM 统计测试 + Robolectric UI 测试
./gradlew :app:assembleRelease        # release 包
```

release 签名读取仓库外的 `~/.config/athena-keystores/randomstring-release.properties`
（键：`storeFile` / `storePassword` / `keyAlias` / `keyPassword`）。文件不存在时 release 构建产物未签名。
keystore 与口令不随仓库分发。

## 许可

Copyright (C) 2026 FlexiAtom

本程序在 **AGPL-3.0-or-later** 条款下分发，全文见 [LICENSE](LICENSE)。

---

A tiny Android random-string generator: length 6–9 picked at random per press, each
character independently and uniformly from `A-Za-z0-9`. Nothing is recorded or persisted.
