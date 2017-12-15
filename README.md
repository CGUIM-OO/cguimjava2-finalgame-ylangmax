# 長庚資管 物件導向程式設計 期末專題說明

繳交期限：1/9 (二) 11:59pm，**不接受遲交**

## 玩家程式碼

1. 點作業[連結](https://classroom.github.com/a/TGVlEfnb)，接受作業，並用github desktop將作業Clone到本機端資料夾CGUIMJava2_FinalGame (自行新增此資料夾)
2. 新增一Java Project名為FinalGame
3. 將Dealer.java, Player.java, PlayerEx.java, Person.java, FinalGame.java拖拉到FinalGame Project中（選link，並注意拖拉到src內）
4. 將Class資料夾內的Class匯入Java Project以利測試
   - 對著FinalGame Project按右鍵，選Properties > Java build path, 然後 "Add External Class Folder"
   - 選擇剛剛本機端資料夾CGUIMJava2_FinalGame中的Class資料夾，內含四個Classes
5. 新增一個Java class，名稱為Player_+學號，繼承Player
   - 如果學號為B96123，Class名稱則為Player_B96123
   - 可參考範例PlayerEx.java
6. 在Player_+學號.java中，新增子類別的建構子（constructor），並將姓名帶入
   - 範例 super("Yi-Ju Tseng (Student ID)", chips);
   - 範例 super("xx-xx xxxxx (B96123)", chips);
7. 實作abstract methods們，想辦法贏得遊戲！
8. 請不要改除了Player_+學號.java以外的任何程式碼，會造成比賽無法順利執行

## 戰略報告說明

請詳細說明你如何客製化Player class中的兩個abstract methods，依客製化程度、邏輯說明清晰程度、是否有按照規定寫（**檔名為學號.pdf，內容務必包含學號姓名系級，12號字，不超過四頁A4**）等要素給分。

## 比賽規則（參考FinalGame.java）

- 抽籤兩兩比賽
- 每局起始籌碼爲1000元
- 每局共有4副牌，玩到有一方沒有籌碼，或是牌發到只剩下1/4副為止
- 每局結束後，剩下較多籌碼的人贏得該局
- 先贏得200局的人就勝出
- 1/15課程公佈籤表，當場比賽！

## 評分標準：

- 佔總成績20%，其中比賽占50%，戰略報告佔50%
- 比賽部分，沒有Compilation error且可完成比賽的Player檔案，可得80分基本分
- 每次晉級加3分；前4名各拿95，93，90，90分，且**要上台分享作戰策略**

## 作業繳交

Push **Player+"_"+學號.java**檔，以及**作戰策略（檔名為學號.pdf）** 到GitHub作業Repo

注意: 

**Player+"_"+學號.java**檔，以及**作戰策略（檔名為學號.pdf）** 需移動到本機端資料夾CGUIMJava2_FinalGame ，才能到GitHub Desktop完成commit、push並上傳

