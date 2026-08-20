# データベース設計

[要件定義書](requirements.md)の詳細ドキュメント。

## ER図(データの関連)

```mermaid
erDiagram
    BOARD ||--o{ LIST : "持つ"
    LIST ||--o{ CARD : "持つ"
    CARD }o--o{ LABEL : "付与される"

    BOARD {
        string id PK
        string title
        string createdAt
    }
    LIST {
        string id PK
        string boardId FK
        string title
        int order
        string createdAt
    }
    CARD {
        string id PK
        string listId FK
        string title
        string description
        string dueDate
        int order
        string createdAt
        string updatedAt
    }
    LABEL {
        string id PK
        string name
        string color
    }
```
- 1つのボードは複数のリストを持つ。
- 1つのリストは複数のカードを持つ。
- 1枚のカードには複数のラベルを付けられ、1つのラベルは複数のカードに付けられる(多対多)。データベース上はカードとラベルを結びつける中間テーブルで管理する。

## データ項目

型はPostgreSQLでの実装を想定した型で記載する(id列はUUID型、日時はtimestamp型とする)。

### Board(ボード)
| 項目 | 型 | 説明 |
| --- | --- | --- |
| id | UUID | 一意なID |
| title | text | ボード名 |
| createdAt | timestamp | 作成日時 |

### List(リスト)
| 項目 | 型 | 説明 |
| --- | --- | --- |
| id | UUID | 一意なID |
| boardId | UUID | 所属するボードのID |
| title | text | リスト名 |
| order | integer | ボード内での並び順 |
| createdAt | timestamp | 作成日時 |

### Card(カード)
| 項目 | 型 | 説明 |
| --- | --- | --- |
| id | UUID | 一意なID |
| listId | UUID | 所属するリストのID |
| title | text | カードタイトル |
| description | text | 説明文 |
| dueDate | date \| null | 期限日 |
| order | integer | リスト内での並び順 |
| createdAt / updatedAt | timestamp | 作成日時・更新日時 |

### Label(ラベル)
| 項目 | 型 | 説明 |
| --- | --- | --- |
| id | UUID | 一意なID |
| name | text | ラベル名 |
| color | text | 表示色 |

### CardLabel(カードとラベルの中間テーブル)
| 項目 | 型 | 説明 |
| --- | --- | --- |
| cardId | UUID | カードのID |
| labelId | UUID | ラベルのID |
