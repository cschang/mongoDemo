# mongoDemo
### 功能
- 取得linebot訊息
- 推送訊息
- 儲存訊息至MongoDB
- 讀取、刪除MongoDB資料
### API
| Method | url            | 功能             |
|--------|----------------|-----------------|
| GET    | /line/{userID} | user訊息         |
| DELETE | /line/{userID} | 刪除user         |
| POST   | /member        | 儲存或更新人員訊息  |

### flowchart
```mermaid
flowchart LR;
%% Colors %%
classDef blue fill:#2374f7,stroke:#000,stroke-width:2px,color:#fff
classDef pink fill:#eb3dd6,stroke:#000,stroke-width:2px,color:#fff
classDef orange fill:#fc822b,stroke:#000,stroke-width:2px,color:#fff
classDef red fill:#ed2633,stroke:#000,stroke-width:2px,color:#fff
classDef green fill:#16b522,stroke:#000,stroke-width:2px,color:#fff
%% GOALS DATABASE %%
%% Goals & Projects %%
user:::pink <--> linebot:::orange;
linebot --receive-->s[server]:::blue;
linebot <-.push..-s;
s <--> DB[(mongoDB)]:::green

```


