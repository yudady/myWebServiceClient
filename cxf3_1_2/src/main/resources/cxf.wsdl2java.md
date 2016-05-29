# wsdl2java用法

再 src/main/java 目錄下執行  wsdl2java -verbose -d . -ant MyService.wsdl


```
wsdl2java -p com -d src -all aa.wsdl

-verbose 顯示詳細訊息
-p 指定其wsdl的命名空間，也就是要生成代碼的包名:
-d 指定要產生代碼所在目錄
-client 生成客戶端測試web service的代碼
-server 生成服務器啟動web service的代碼
-impl 生成web service的實現代碼
-ant 生成build.xml文件
-all 生成所有開始端點代碼
```


### command

```
wsdl2java -verbose -p com.foya -d . -server -client -ant -all MyService.wsdl


---

wsdl2java -verbose -p com.foya.all -d . -all MyService.wsdl

wsdl2java -verbose -p com.foya.server -d . -server -impl MyService.wsdl

wsdl2java -verbose -p com.foya.client -d . -client MyService.wsdl


```
