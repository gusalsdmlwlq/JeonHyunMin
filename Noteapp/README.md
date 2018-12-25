![메인 화면](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Noteapp/1.png)
![메모](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Noteapp/2.png)
![검색](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Noteapp/3.png)
* 기능
1. 달력 - 메인 화면에 달력을 표시. 
          오늘 날짜는 굵게 표시. 
          날짜를 클릭하면 해당 날짜의 저장된 메모 목록 불러오기.
2. 메모 - 날짜별로 메모를 작성후 저장 가능. 
          달력의 날짜를 클릭하면 해당 날짜에 저장된 메모를 불러올 수 있음.
          메모를 작성하고 저장하면 메모를 기록한 시간도 같이 저장.
3. 검색 - 메인 화면에서 메모 검색 가능. 
          검색창에 메모의 제목을 입력하면 해당 메모를 불러올 수 있음.
4. 점심 메뉴 뽑기 - 원하는 메뉴를 입력해서 저장 가능. 
                   저장된 메뉴들 중에서 랜덤으로 하나의 메뉴를 뽑아주는 기능.
                   매일 '점심에 뭐 먹지?' 하는 고민을 없애주는 기능.

* 노트 앱 사용
1. localhost를 사용하지 않고 index.html 파일을 열어서 앱을 실행시킴.
2. 메인 화면의 달력의 날짜를 클릭하면 메모 작성 페이지로 이동함.
3. 메모를 작성하고 'save note'를 클릭해 메모를 저장.
4. 작성한 메모는 브라우저의 localstorage에 저장되므로 로컬데이터를 삭제하면 메모가 지워짐.
5. 메인 화면의 검색 창에 저장된 메모의 제목을 입력하고 '검색'을 클릭해 메모를 불러올 수 있음.

* 앱 설명
1. html, javascript, css를 사용함.
2. 프론트엔드와 백엔드를 구분 없이 하나로 앱을 개발.
3. DB는 브라우저의 localstorage를 사용.
4. index.html 파일로 앱을 실행시키고 링크를 통해 notelist.html 파일을 화면에 띄움.
5. notelist.html 파일에서 app.js와 app.css를 불러와 노트의 기능을 수행.
6. notelist.html이 화면에 띄워지면 app.js 파일이 localstorage에서 데이터를 받아와서 저장된 메모들을 불러와 화면에 표시함.
7. 메모를 작성하고 저장하면 localstorage에서 받아온 데이터를 수정하고 다시 localstorage로 데이터를 전송하고 저장함.
8. 검색창에 키워드를 입력하고 '검색'을 클릭하면 searchlist.html을 화면에 띄우고 search.js를 통해 localstorage에서 데이터를 
   받아오고 키워드를 포함한 제목의 메모들을 화면에 표시함.
9. searchlist.html에서도 메모를 수정하거나 삭제하면 localstorage로 데이터를 전송하고 저장함.

* 결과물
1. 메모의 작성, 저장, 삭제, 수정 구현.
2. 달력 기능 구현.
3. 오늘 날짜 표시 구현.
4. 검색 기능 구현.
5. 메모를 작성, 수정시 시간이 저장되는 기능 구현.
6. electron을 사용해 패키징 완료. cmd창에서 'electron .' 명령어로 앱 실행 가능.
7. electron-packager를 사용해 실행파일 만듬. electron 실행파일로 앱 실행 가능.
8. 날짜별 메모는 구현하지 못하고 통합 메모로 구현.
9. 추가 기능으로 점심 메뉴 뽑기를 만들 계획이었으나 구현하지 못함.
