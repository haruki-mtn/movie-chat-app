# movie-chat-app

これは、サーブレット・JSP を使って作成した映画チャットアプリケーションです。
単なる作品情報の取得にとどまらず、作品のブックマーク管理や作品ごとにチャットを用いた、感想・疑問点の共有が可能です。

## 使用技術

- Java SE 21 (OpenJDK 21)
- Apache Tomcat 10
- MariaDB Server 10.x

## 主な機能

- 作品の一覧表示
- 作品の詳細表示
- 作品ごとのチャット投稿
- ブックマークの登録・削除

## 使い方

1. ログインページから新規登録ページに遷移して新規ユーザー登録
2. ログインページでログイン
3. トップページで作品一覧を確認（検索可能）
4. 作品のサムネイルから作品詳細を確認
5. 作品詳細からチャットページに遷移

## セットアップ手順

### WSL2/Ubuntu で動かす場合

JDK 21 のインストール

```sh
sudo apt install -y openjdk-21-jdk
```

Tomcat 10 のインストール

```sh
sudo apt install -y tomcat10
```

MariaDB のインストール

```sh
sudo apt install -y mariadb-server
```

JDBC ドライバの導入

```sh
sudo apt install -y libmariadb-java
```

JDBC ドライバの配置

```sh
# Tomcat 実行用
cp /usr/share/java/mariadb-java-client.jar src/main/webapp/WEB-INF/lib/

# 開発用（VSCode / コンパイル）
cp /usr/share/java/mariadb-java-client.jar lib/
```

JSTL の導入

```sh
# JSTL API
curl -O https://repo1.maven.org/maven2/jakarta/servlet/jsp/jstl/jakarta.servlet.jsp.jstl-api/3.0.0/jakarta.servlet.jsp.jstl-api-3.0.0.jar

# JSTL 実装
curl -O https://repo1.maven.org/maven2/org/glassfish/web/jakarta.servlet.jsp.jstl/3.0.1/jakarta.servlet.jsp.jstl-3.0.1.jar
```

JSTL の配置

```sh
mv jakarta.servlet.jsp.jstl-*.jar src/main/webapp/WEB-INF/lib
```

BCrypt の導入

```sh
curl -O https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar
```

BCrypt の配置

```sh
# Tomcat 実行用
cp jbcrypt-0.4.jar src/main/webapp/WEB-INF/lib/

# 開発用（VSCode / コンパイル）
cp jbcrypt-0.4.jar lib/
```

コンテキストの設定

```sh
sudo vi /etc/tomcat10/Catalina/localhost/movie-chat-app.xml
```

```xml
<Context
	docBase="/home/user-name/projects/movie-chat-app/src/main/webapp"
	reloadable="true"
/>
```

Visual Stuido Code を使用している場合、サーブレットAPIをプロジェクトに配置しないとエラーが出る

```sh
cp /usr/share/tomcat10/lib/servlet-api.jar lib/
```

Visual Stuido Code を使用している場合、settings.json に以下を追加

```json
"java.project.sourcePaths": [
    "src"
],
"java.project.referencedLibraries": [
    "lib/**/*.jar"
],
"emmet.includeLanguages": {
    "jsp": "html"
}
```

Tomcat のログ・標準出力を表示

```sh
tail -f /var/log/tomcat10/catalina.out
```

サーバーの再起動

```sh
sudo systemctl restart tomcat10
```

データベースサーバーの再起動

```sh
sudo systemctl restart mariadb
```

サーバ立ち上げ後は http://localhost:8080/movie-chat-app にアクセス

## ディレクトリ構成

```
movie-chat-app/
├─ .vscode/
│   └─ settings.json
├─ db/
│   ├─ 00_reset_tables_dev.sql
│   ├─ 01_create_database.sql
│   └─ 02_create_tables.sql
├─ lib/
│   ├─ jbcrypt-0.4.jar
│   ├─ mariadb-java-client.jar
│   └─ servlet-api.jar
├─ src/
│   └─ main/
│       ├─ java/
│       │   └─ jp/mtn/moviechat/
│       │       ├─ dao/
│       │       ├─ filter/
│       │       ├─ model/
│       │       └─ servlet/
│       └─ webapp/
│           ├─ css/
│           ├─ images/
│           ├─ js/
│           ├─ WEB-INF/
│           │   ├─ classes/
│           │   ├─ jsp/
│           │   ├─ lib/
│           │   │   ├─ jakarta.servlet.jsp.jstl-3.0.1.jar
│           │   │   ├─ jakarta.servlet.jsp.jstl-api-3.0.0.jar
│           │   │   ├─ jbcrypt-0.4.jar
│           │   │   └─ mariadb-java-client.jar
│           │   └─ web.xml
│           └─ index.jsp
├─ .gitignore
├─ build.sh
└─ README.md
```
