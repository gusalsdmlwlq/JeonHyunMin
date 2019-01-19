-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- 생성 시간: 17-12-18 22:53
-- 서버 버전: 10.0.31-MariaDB
-- PHP 버전: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `webapp`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `board`
--

CREATE TABLE `board` (
  `no` int(11) NOT NULL,
  `title` text NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `time` text NOT NULL,
  `text` text NOT NULL
);

--
-- 테이블의 덤프 데이터 `board`
--

INSERT INTO `board` (`no`, `title`, `user_id`, `time`, `text`) VALUES
(1, '창덕궁/창경궁 후기', 'admin', '2017-12-18 AM 12:33:33', '\r\n겨울의 한적한 고궁을 거니는 경험은 신비로운 느낌을 줍니다. 수십 수백년전에 수많은 사람들이 다니던 혹은 금기시되어 밟을 수 없던 곳을 아무렇지 않게 걷고 있다는 것은 때로는 아련함마저 들게한다. \r\n명정전에서 조선의 왕들이 봤을 풍경은 내가 그곳에서 본 풍경과는 달랐겠지만 왕의 시선이 이랬겠구나 라는 느낌을 받을 수 있었다. 갔었을때는 아쉽게도 부분 구역이 AI로 인해 통제되고 있어서 더 많은곳은 가보질 못했던건 아쉬운 부분이었다.\r\n창경궁과 창덕궁은 연결되어 있어서 굳이 창경궁을 돌고 나갈 필요가 없이 바로 창덕궁으로 넘어갈 수 있다. \r\n궁 내가 모두 자유관람인 창경궁과는 달리 창덕궁은 후원은 가이드와 함께 가는 제한적 관람 구역이다. 창경궁의 후원은 유일하게 남은 궁궐 내 정원인데 생각했던것보다 더 아름답게 꾸며져 있었다. 창덕궁을 가면 꼭 후원까지 둘러보자.\r\n창덕궁이 좀 더 건물이 많고 웅장해서 창경궁 창덕궁 순으로 보는게 더 좋은거 같다.\r\n아 참 주변에 지하철역이 다 애매하게 있으니 적절하게 잘 살펴보고 가는걸 추천'),
(2, ' 국립 중앙박물관 후기', 'admin', '2017-12-18 AM 12:33:55', '이촌역에서 내리자마자 지하통로의 표지판을 걸으며 따라가기만 하면 나오는 곳. 무난한 현대적 디자인을 가진 이곳은 국립 중앙박물관이다. \r\n수많은 우리나라의 역사적 유물들도 볼 수 있고 또 다양한 체험전/초청전을 하는데 국내의 문화에 대한 테마를 잡기도 하지만 이집트전 같이 외국의 유명한 문화에 대한 전시도 자주한다. 이집트전을 연다고 해서 가봤었는데, 사람들이 몰려서 한시간정도 대기를 했다.\r\n대기순번을 기다려서 들어갔는데 전시장 안에도 사람들이 엄청 많아서 거의 밀리다시피 관람을 하고 나왔다. 고대 이집트 (대략 기원전 25세기쯤부터 프톨레마이오스 왕조 멸망할때까지의 유물들)의 수많은 유물을 봤다.\r\n재밌는 기획전이었는데 한가지 아쉬웠던건 기념품이 너무 별로였다는 점.\r\n그리고 이거 보고 국내 유물보는데 좀 초라하던게...\r\n어쨌건 재밌게 보고 왔어요!'),
(3, '청키면가 후기', 'admin', '2017-12-18 AM 12:34:01', '종각역 근처, 점심시간엔 수많은 회사원들로 가득하다. 가게 밖까지 서서 기다리는 사람들이 보이는 식당이 하나 있다. 완탕면 전문점인 \'청키면가\'다. \r\n가봐야지 하고 있었는데 이번에 근처에 갈 일이 있어서 이 기회에 먹어보게 됬다.\r\n동행없이 가서 다양한 메뉴를 못먹어서 아쉬웠지만 별 수 있나... 어쨌건 가장 대표적인 새우완탕면 하나랑 수교(돼지고기 만두)를 시켰다. \r\n면은 생각보다 되게 쫄깃했다고 해야하나... 툭툭 끊어지는데 그러면서도 탄력은 충분하다. 국물은 새우 육수의 짭잘함이 느껴지지만 비린맛 없이 깔끔하면서 담백하다. 아무생각없이 계속 들어가는 맛이다. 또한 들어있는 완탕은 새우맛을 그대로 담고있어서 뒤에 서술할 수교에 비해서 개인적으론 더 좋았다.\r\n수교는 되게 밸런스가 잘 잡혀있었다. 비릴법 한데도 그냥 먹었을때도 돼지고기의 풍미는 살아있지만 비린맛은 싹 잡혀있다. 혼자서 수교 한 세트를 다먹다보니 끝에는 왠지 모르게 물리긴 하던데 원래 수교는 혼자서 다먹는건 아닌거같으니 아 완탕도 먹어서 그런걸지도. \r\n처음에만 맛본다고 음미하다가 너무 맛있어서 정신 차려보니 다 먹었던...\r\n다들 근처 가시면 한 번씩 가보는거 추천합니다.'),
(4, '강남 터미널 후기', 'guest2', '2017-12-18 PM 10:23:08', '깔끔한 곳이네요\r\n\r\n좀 오래된 듯한 동서울 터미널에 비하면 거의 최신식...\r\n\r\n2호선이 바로 있어서 지하철 타기도 좋고. 대신 다와가지만 차가 너무 밀려서 서울 들어왔다고 금방 도착하는건 아니라는게...\r\n\r\n좋아요 굳!'),
(5, '안녕하세요 방금 가입했어요~', 'guest1', '2017-12-18 PM 10:27:09', '둠칫 둠칫'),
(6, 'Oh! 한쿡 조하요 김치 마시써요 연예가중계 사랑해요', 'guest3', '2017-12-18 PM 10:30:32', '헬'),
(7, '대한민국 만세', 'guest3', '2017-12-18 PM 10:32:15', '만세'),
(8, '김치 좋아요', '1234', '2017-12-18 PM 10:33:57', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `food`
--

CREATE TABLE `food` (
  `food_name` varchar(15) NOT NULL,
  `food_par1` varchar(3000) NOT NULL,
  `food_par2` varchar(3000) NOT NULL,
  `pic1` varchar(140) NOT NULL,
  `pic2` varchar(140) NOT NULL,
  `pic3` varchar(140) NOT NULL,
  `pic4` varchar(140) NOT NULL,
  `pic5` varchar(140) NOT NULL,
  `restaurant` varchar(40) DEFAULT NULL,
  `restaurant_name1` varchar(200) DEFAULT NULL,
  `restaurant_name2` varchar(200) DEFAULT NULL,
  `restaurant_name3` varchar(200) DEFAULT NULL
);

--
-- 테이블의 덤프 데이터 `food`
--

INSERT INTO `food` (`food_name`, `food_par1`, `food_par2`, `pic1`, `pic2`, `pic3`, `pic4`, `pic5`, `restaurant`, `restaurant_name1`, `restaurant_name2`, `restaurant_name3`) VALUES
('갈비', '동물의 늑골 부위에 있는 고기 부위를 말한다. 원래 소의 갈비를 지칭하는 말이었으나 하지만 소고기를 써서 만드는데 소고기 값이 비싸니 사람들이 소갈비 대신 돼지갈비를 이용하기 시작했고 오늘날에 와서는 갈비하면 소갈비가 아닌 돼지갈비를 일컫는 경우도 많아졌다. 돼지갈비 역시도 목살이나 전지 등을 살이 별로 없는 등갈비의 살 부분에 붙여 갈비라고 파는 경우도 있는데 이쪽은 기원 자체가 상당히 차이가 있는 음식이다. 소갈비를 먹고 싶은데 가격이 비싸서 돼지고기의 여러 부위를 상대적으로 두텁게 썰어 갈비 양념을 해서 재워 구운 것을 돼지갈비라 부르기 시작했다는 설이다. ', '숯불 위에 석쇠를 올려놓고 그 위에 갈비살을 얹혀서 숯불에 구워 익혀먹는 고기 요리이다. 그냥 먹어도 맛있지만 상추나 깻잎을 포함한 여러 쌈채소에 마늘, 된장, 싸먹을 수 있는 여러 야채들과 함께 먹으면 더 맛있는 음식. 불고기와 함께 외국인들이 자주 찾는 메뉴. 소갈비는 물론 돼지갈비도 구워서 먹을 수 있다. 단, 소갈비의 경우 일찍 익는 편이지만 돼지갈비는 좀 더 익힌 후 먹는 게 좋다. 김치와 같은 음식에 비해 외국인들이 접하기에도 좋은 한식이다.', 'sam/galbi/img(1).jpg', 'sam/galbi/img(2).jpg', 'sam/galbi/img(3).jpg', 'sam/galbi/img(4).jpg', 'sam/galbi/img(5).jpg', '맛집', '벽제갈비\r\n주소  서울시 송파구 양재대로 71길 1-4\r\n메뉴 LA갈비 16,000원    양념갈비 25,000원 (소갈비)\r\n시간 11:30 ~ 21:00\r\n<a href=\"https://guide.michelin.co.kr/ko/restaurant/byeokje-galbi/\" target=parent>링크</a>', '돈뼈락 연탄갈비\r\n주소  서울시 관악구 봉천동 1666-26\r\n메뉴  돼지갈비 12,000원    \r\n시간  11:30 ~ 20:00\r\n<a href=\"https://goo.gl/hjCjky\" target=parent>링크</a>', '삼도갈비\r\n주소 서울시 강남구 역삼동 727-1\r\n메뉴  수제돼지갈비 18,000원    양념갈비 32,000원    생갈비 34,000원\r\n시간  10:00 ~ 5:00 (일요일 10:00 ~ 22:00)\r\n<a href=\"https://goo.gl/5UwmmJ\" target=parent>링크</a>\r\n'),
('냉면', '냉면의 기원은 고려시대 중기의 평양에서 유래한다. 1973년 북한에서 간행된 요리 서적에 의하면, 평양냉면은 현재 평양의 대동강구역 의암동 지역에서 처음 나왔다고 하며, 메밀 수제비 반죽을 국수로 뽑은 것이 시초라 한다. 고려 중기의 냉면을 기록한 고문헌에는 \'찬 곡수(穀水)에 면을 말아 먹는다\'는 취지의 기술이 있다.', '냉면은 칡, 메밀, 감자, 고구마 등의 다양한 가루를 이용하여 만든 면(麵)과, 썬 오이 등의 생야채와 배 한 조각, 그리고 고기와 삶은 달걀로 이루어진 음식이다. 냉면은 육수에 따라 물냉면과 비빔냉면으로 분류된다. 물냉면은 보통 위의 재료가 차가운 육수(보통 소고기 육수)에 담겨 나오는 형태이다. 물냉면은 육수 제조법이나, 들어가는 고기나 야채의 종류에 따라 그 종류가 세분화되기도 한다.', 'sam/nangmun/img(1).jpg', 'sam/nangmun/img(2).jpg', 'sam/nangmun/img(3).jpg', 'sam/nangmun/img(4).jpg', 'sam/nangmun/img(5).jpg', '맛집', '평양면옥\r\n주소  서울시 중구 장충단로 207\r\n메뉴  냉면 11,000원  편육 25,000원\r\n시간  11:00 ~ 21:10\r\n<a href=\"https://pyungyangmyunok.modoo.at/\" target=parent>홈페이지</a>', '봉피양\r\n주소  서울시 양재대로 71길 1-4\r\n메뉴  평양냉면 13,000원  비빔냉면 12,000원\r\n시간  11:00 ~ 21:30\r\n<a href=\"http://www.bjgalbi.com/\" target=parent>홈페이지</a>', '우래옥\r\n주소  서울시 중구 창경궁로 62-29\r\n메뉴  평양냉면 12,000원  비빔냉면 12,000원\r\n시간  11:30 ~ 21:00\r\n<a href=\"https://guide.michelin.co.kr/ko/restaurant/woo-lae-oak/\" target=parent>링크</a>'),
('불고기', '쇠고기를 양념에 재우고 야채를 넣고 자작하게 만든 한국의 전통 요리이다. 돼지고기로 만든 것은 따로 돼지불고기라고 한다. 구이에는 결합조직이 적고 지방이 조금씩 산재해 있는 고기가 맛이 있고 연하기 때문에 안심이나 등심 등의 부위가 가장 많이 사용된다. 보통 \'불고기\'라고 하면 간장 양념을 쇠고기에 재운 소불고기를 가리키는 경우가 많으며, 이외에 고추 양념을 쓴 돼지불고기가 있고, 이 외에 오리고기, 닭고기가 주로 쓰이고 최근에는 해산물을 이용한 불고기도 나오고 있다.', '불고기라는 명칭은 과거 불에 구워먹는 고기라는 뜻으로 생겨났다가, 점차 양념한 고기를 익히는 의미로 바뀌었으며, 양념하지 않고 소금간만 하고 굽는 고기는 소금구이라고 칭하고 있다. 여기서 양념한 고기는 특별히 정해진 바가 없으며 어떠한 양념을 어떠한 고기 (주로 육류)에 발라 구워내는 음식을 모두 불고기로 표현하고 있다. 단, 고기에 통뼈가 붙어있거나 하는 경우는 갈비라는 표현을 쓰기도 한다.', 'sam/bulgogi/img(1).jpg', 'sam/bulgogi/img(2).jpg', 'sam/bulgogi/img(3).jpg', 'sam/bulgogi/img(4).jpg', 'sam/bulgogi/img(5).jpg', '맛집', '삼원가든\r\n주소 서울시 강남구 언주로 835 \r\n메뉴  불고기 39,000원  양념갈비 44,000원\r\n시간  11:50 ~ 21:30\r\n<a href=\"http://www.samwongarden.com/\" target=parent>홈페이지</a>', '역전회관\r\n주소  서울시 마포구 토정로 37길 47\r\n메뉴  바싹불고기 30,000원  매운 바싹불고기 30,000원\r\n시간  점심 11:00 ~ 14:30  저녁 17:00 ~ 21:00\r\n<a href=\"http://www.yukjeon.com/\" target=parent>홈페이지</a>', '아리랑\r\n주소  서울시 중구 남대문로 7길 23\r\n메뉴  런치한우 불고기 12,000원   아리랑 양념불고기 30,000원\r\n시간  11:30 ~ 23:00\r\n<a href=\"https://www.arirangcook.com/\" target=parent>홈페이지</a>'),
('비빔밥', ' 밥에 각종 나물과 고추장, 그리고 기타 재료(계란 등)을 넣고 비벼서 만드는 요리다. 한국의 전통 음식이며, 간편하고 손쉽게 만들어 먹을 수 있다는 장점이 있다. 보통 정통식 나물비빔밥은 명절 때 많이 먹게 된다.\r\n대략 한 500년전부터 비빔밥을 즐겨 먹었던 것으로 추정된다. 그도 그럴 것이 밥에 각종 반찬과 장을 넣고 비벼먹는 손이 많이 안 가는 요리이기 때문. 나물이 많이 들어가 밸런스 있는 영양소의 섭취가 이뤄지며 먹는 양에 비해 칼로리가 적은것이 특징이다. ', '16세기에는 밥에 고기와 채소를 넣고 비벼 먹던 것을 혼돈반(混沌飯)이라 부르다가 18세기부터는 골동반(骨董飯)이라고 많이 불렀는데, 이는 중국 기록에 \"강남 사람들은 이것저것 한데 넣고 끓여 먹는데, 바로 골동갱(骨董羹)이다\"라고 한 기록에 근거한 것으로 보인다. 여기서 갱(羹)은 국을 뜻하는 한자이다. 골동(骨董)은 또 골동(汨董)이라고도 했는데, 어지러울 골(汨)이다. 이후의 시의전서를 보면 골동반을 또 부븸밥이라고 했다는 기록이 있다. 여기서 유래된 것이 비빔밥이라는 게 정설이다.', 'sam/bibimbab/img(1).jpg', 'sam/bibimbab/img(2).jpg', 'sam/bibimbab/img(3).jpg', 'sam/bibimbab/img(4).jpg', 'sam/bibimbab/img(5).jpg', '맛집', '목멱산방\r\n주소  서울시 중구 남산공원길 125-72\r\n메뉴  산방비빔밥 7,000원, 불고기비빔밥 9,000원, 육회비빔밥 11,000원\r\n시간  11:00 ~ 20:00\r\n<a href=\"http://mmmroom.com/\" target=parent>홈페이지</a>', '하모\r\n주소  서울시 강남구 언주로 819 2층\r\n메뉴  전주비빔밥 12,000원, 헛제사밥 10,000원\r\n시간  점심 11:30 ~ 14:30   저녁 17:00 ~ 21:00 / 주말 11:30 ~ 21:00\r\n<a href=\"https://hamokitchen.modoo.at/\" target=parent>홈페이지</a>', '한일관\r\n주소  서울시 강남구 압구정로 38길 14\r\n메뉴  궁중 비빔밥 13,000원\r\n시간  11:30 ~ 21:00\r\n<a href=\"http://www.hanilkwan.co.kr/\" target=parent>홈페이지</a>'),
('전', '전은 생선이나 고기, 채소 등을 얇게 썰거나 썰지 않고 양념을 한 뒤, 밀가루와 달걀물을 씌워 기름에 지진 음식이다. 전유라고도 부르며, 부침개와 달리 재료의 형태를 최대한 유지한다. 굴전·새우전·버섯전·고추전·호박전 등이 대표적이다. 조선왕조 궁중음식에서는 얇게 저민 고기나 생선 따위에 밀가루를 묻히고 달걀 푼 것을 씌워 기름에 지진 음식을 저냐로 부른다. 저냐를 전유어나 전유화라 부르기도 한다.', '파전, 부추전, 김치전, 녹두전, 수수부꾸미 등 밀가루나 기타 반죽이 주가 되게끔 사용하며 넓적하게 부쳐내는 종류와 호박전, 생선전, 산적, 동그랑땡(고기완자), 배추전(경상도)등, 주재료에 달걀옷을 입히고 밀가루를 다시 묻혀 부쳐내는 종류로 나눌 수 있는데 사실상 두 종류는 조리법이 다른 음식이다. 그러나 밀가루를 쓰는 것과 기름을 두르고 부쳐내는 것은 같다. 반죽이 메인인가 속재료가 메인인가 하는 차이점이 있다. 또한 전자를 부침개라고 부르는 경우가 더 많은 편.', 'sam/jun/img(1).jpg', 'sam/jun/img(2).jpg', 'sam/jun/img(3).jpg', 'sam/jun/img(4).jpg', 'sam/jun/img(5).jpg', '맛집', '전주전집\r\n주소  서울시 동작구 사당동 1032-1\r\n메뉴  모듬전 18,000원  고추천 13,000원\r\n시간  15:30 ~ 02:00\r\n<a href=\"https://goo.gl/aektsv\" target=parent>링크</a>', '박가네 빈대떡\r\n주소  서울시 종로구 예지동 293-1 광장시장\r\n메뉴  빈대떡 4,000원 \r\n시간  09:00 ~ 22:00\r\n<a href=\"https://goo.gl/f24ct7\" target=parent>링크</a>', '원조녹두\r\n주소  서울시 중구 입정동 272-8\r\n메뉴  해물파전 9,000원   고기파전 8,000원   고기녹두 8,000원\r\n시간  주중 04:00 ~ 23:00   주말 01:00 ~ 23:00\r\n<a href=\"https://goo.gl/wCd6Zi\" target=parent>링크</a>'),
('족발', '한국 전쟁 당시 피난민들이 대거 서울로 유입되었는데 장충동 일대에 적산 가옥, 즉 일본인들이 남기고 간 빈 집이 많아서 들어가 살기 시작했고 자연스레 피난민촌이 형성이 되었다. 이후 생계를 위해 음식장사를 했는데 이 때 돼지족도 같이 삶아 판 것으로 보인다. 족발은 장육의 영향을 매우 많이 받았다. 한국화되는 과정에서 향이 약해지기는 했으나 80년대까지만 해도 중국식의 강한 팔각향이 나는 족발이 드물지 않았던것으로 보아서 중국의 영향을 많이 받았다고 보인다. ', '중국의 축제 음식인 장육과도 비슷한 것으로 보아 족발의 기원은 중화문명의 영향을 강하게 받은 음식 문화로 추측되기도 한다. 더불어 브라질에도 있고, 일본의 오키나와 요리인 테비치소바(てびちそば), 독일의 요리 슈바인스학세(Schweinshaxe), 아이스바인(Eisbein), 오스트리아의 슈텔체(Stelze), 체코의 꼴레뇨(Koleno) 그리고 태국 요리 카오카무(ข้าวขาหมู)도 돼지의 다리를 이용한 요리이다. 아일랜드 요리에도 크루빈스(Crubeens)라는 비슷한 음식이 있다.', 'sam/jokbal/img(1).jpg', 'sam/jokbal/img(2).jpg', 'sam/jokbal/img(3).jpg', 'sam/jokbal/img(4).jpg', 'sam/jokbal/img(5).jpg', '맛집', '할매집\r\n주소  서울시 종로구 내자동 사직로12길 1-5\r\n메뉴  족발 30,000원, 35,000원\r\n시간  12:00 ~ 21:30\r\n<a href=\"https://guide.michelin.co.kr/ko/restaurant/halmaejip/\" target=parent>링크</a>', '오가네 족발\r\n주소  서울시 강남구 도곡동 416-11\r\n메뉴  족발 33,000원, 38,000원\r\n시간  10:00 ~ 22:00\r\n<a href=\"https://guide.michelin.co.kr/ko/restaurant/ogane-jokbal/\" target=parent>링크</a>', '만족 오향 족발\r\n주소  서울시 중구 서소문로 134-7\r\n메뉴  족발 29,000원, 34,000원, 49,000원\r\n시간  11:30 ~ 22:30\r\n<a href=\"https://guide.michelin.co.kr/ko/restaurant/manjok-ohyang-jokbal/\" target=parent>링크</a>');

-- --------------------------------------------------------

--
-- 테이블 구조 `food_en`
--

CREATE TABLE `food_en` (
  `food_name` varchar(15) NOT NULL,
  `food_par1` varchar(3000) NOT NULL,
  `food_par2` varchar(3000) NOT NULL,
  `pic1` varchar(140) NOT NULL,
  `pic2` varchar(140) NOT NULL,
  `pic3` varchar(140) NOT NULL,
  `pic4` varchar(140) NOT NULL,
  `pic5` varchar(140) NOT NULL,
  `restaurant` varchar(40) DEFAULT NULL,
  `restaurant_name1` varchar(200) DEFAULT NULL,
  `restaurant_name2` varchar(200) DEFAULT NULL,
  `restaurant_name3` varchar(200) DEFAULT NULL
);

--
-- 테이블의 덤프 데이터 `food_en`
--

INSERT INTO `food_en` (`food_name`, `food_par1`, `food_par2`, `pic1`, `pic2`, `pic3`, `pic4`, `pic5`, `restaurant`, `restaurant_name1`, `restaurant_name2`, `restaurant_name3`) VALUES
('Bibimbap', 'It is a dish made by putting various kinds of herbs, hot pepper paste, and other ingredients (such as eggs) into rice. It is Korean traditional food, and it is easy and easy to make and eat. Ordinary meals and bibimbap are usually eaten during the holidays. It is estimated that he enjoyed bibimbap for about 500 years ago. He would also like to cook a variety of side dishes and bowels to eat the rice is not much to eat. It is characterized by a low intake of nutrients and balanced calorie intake.', 'In the 16th century, it was called chaotong (rice cake) with meat and vegetables in rice, and it was called \"antique rice\" from the 18th century. It is said in the Chinese record, \"Gangnam people put boiled food in one thing, It is an antique gang. \" Here gang (羹) is a kanji for the country. Antiquity is also called antiquity, but it is dizzying. In the following poems, there is a record that the antlers are called \" The thing that comes from here is bibimbap.', 'sam/bibimbab/img(1).jpg', 'sam/bibimbab/img(2).jpg', 'sam/bibimbab/img(3).jpg', 'sam/bibimbab/img(4).jpg', 'sam/bibimbab/img(5).jpg', 'restaurants', 'mogmyeogsanbang\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Jung-gu</a> Namsanparkstreet 125-72\r\nMenu Sangbang Bibimbap 7,000 won, Bulgogi Bibimbap 9,000 won, Bibimb', 'hamo\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Gangnam-gu</a>Unjoo-ro 819 2nd floor\r\nMenu Jeonju Bibimbap 12,000 won, Hut Sisabang 10,000 won\r\nTime Lunch 11:30 ~ 1', 'han-ilgwan\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Gangnam-gu</a> Abgujung-ro 38st. 14\r\nMenu Court Bibimbap 13,000 won\r\nTime 11:30 ~ 21:00\r\n<a href=\"http://www.h'),
('Bulgogi', 'It is a Korean traditional dish made by putting beef in sauce and adding vegetables. It is called pork bulgogi separately made with pork. The roasted meat has few connective tissues and the fat is scattered little by little. The meat is tasted and soft, so rest and relief are most commonly used. In general, \'bulgogi\' refers to bulgogi, which is made with soy sauce spiced beef. In addition, there are pork bulgogi with pepper sauce. In addition, duck meat and chicken are mainly used, and recently bulgogi using seafood is also emerging.', 'The name \"Bulgogi\" is derived from the meaning of meat roasted in the past, gradually changed to the meaning of seasoned meat, and the meat that is not seasoned and seasoned with salt is called grilled fish. Here, the meat that has been seasoned is not specially decided, and all the foods which are baked in some meat (mainly meat) are expressed as bulgogi. However, if the meat is attached to a bone bone, the word rib is used.', 'sam/bulgogi/img(1).jpg', 'sam/bulgogi/img(2).jpg', 'sam/bulgogi/img(3).jpg', 'sam/bulgogi/img(4).jpg', 'sam/bulgogi/img(5).jpg', 'restaurants', 'sam-wongadeun\r\n\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Gangnam-gu</a>Unju-ro 8-35\r\nMenu Bulgogi 39,000 won Seasoned ribs 44,000 won\r\nTime 11:50 - 21:30\r\n<a href', 'yeogjeonhoegwan\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Mapo-gu</a>Tojeong-ro 37st. 47\r\nMenu barbecue 30,000 won Spicy barbecue 30,000 won\r\nTime Lunch 11:00 ~ 14', 'Arirang\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Junggu-gu</a>Namdaemun-ro 7st. 23\r\nMenu Lunch Hanwoo Bulgogi 12,000won Arirang Seasonal Bulgogi 30,000 won\r\nTime '),
('Galbii', 'It Refers to the meat part of the animal\'s ribs. It was originally used to refer to ribs of cattle, but when it comes to making beef, beef is so expensive that people started to use pork ribs instead of small ribs. Today, ribs are more often called pork ribs than small ribs. Pork ribs are also sold as ribs attached to the flesh part of the ribs, such as not having much meat, such as pork neck or front feet. This is a food with a very different origin.', 'It is a meat dish that grills over charcoal fire, grilled on top of it, and grilled on charcoal fire. It\'s delicious even if you eat it, but it is more delicious if you eat it with garlic, miso, and various vegetables that can be eaten in various vegetables including lettuce and sesame leaves. It is a menu that foreigners frequently visit with Bulgogi. cow ribs, as well as pork ribs, can be grilled and eaten. However, in the case of boiled pork ribs, it is better to eat pork ribs after cooking more. It is a good Korean food for foreigners as compared to food like kimchi.', 'sam/galbi/img(1).jpg', 'sam/galbi/img(2).jpg', 'sam/galbi/img(3).jpg', 'sam/galbi/img(4).jpg', 'sam/galbi/img(5).jpg', 'restaurants', 'Byeogjegalbi\r\n\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Songpa-gu</a> YangJae street 71gil 1-4\r\nMenu LA Galbi 16,000 won Seasoned Galbi 25,000 won\r\nTime 11:30 ~ 2', 'Donppyeolag Yeontangalbi\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Gwanak-gu</a>1666-26 Bongcheon-dong\r\nMenu Pork ribs 12,000 won\r\nTime 11:30 ~ 20:00\r\n<a href=\"htt', 'Samdogalbi\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Gangnam-gu</a> Yeoksam-dong 727-1\r\nMenu Homemade pork ribs 18,000 won Seasoned ribs 32,000 Won chicken wings 3'),
('Jeon', 'Jeon is an fried food made by slicing or cutting fish, meat, vegetables, etc., and sprinkling with flour and egg water. It is also called whole milk, and keeps the shape of the material as much as possible. Oyster Jeon, shrimp Jeon, mushroom Jeon,pepper Jeon, and pumpkin Jeon are typical examples. In the Joseon Dynasty court food, flour is buried in thinly sliced meats or fish, and eggs are sprinkled with oil. It is also called the whole language or the whole thing.', 'It is used to make wheat flour and other dough such as PaJeon, BuchuJeon, KimchiJeon, NokduJeon, and SusuBukoomi. It is used as a main ingredient, such as pumpkinJeon, fishJeon, meatJeon, meatball, It can be divided into two types, which are coated and flour-dipped. In fact, the two types are different recipes. But it is the same to use flour and oil. There is a difference that dough is main or ingredient is main. Also, it is more common to call the former as buchimgae.', 'sam/jun/img(1).jpg', 'sam/jun/img(2).jpg', 'sam/jun/img(3).jpg', 'sam/jun/img(4).jpg', 'sam/jun/img(5).jpg', 'restaurants', 'JeonjuJeonjip\r\n\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Dongjak-gu</a> Sadang-dong 1032-1\r\nMenu modumJeon 18,000 won pepperJeon 13,000 won\r\nTime 15:30 ~ 02:00\r\n<', 'baggane bindaetteog\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Jongro-gu</a> Yeji-dong 293-1 Gwangjangmarket\r\nMenu Bindaeok 4,000 won\r\nTime 09:00 to 22:00\r\n<a href=', 'wonjonokdu\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Jung-gu</a>Ipjeong-dong 272-8\r\nMenu Seafood Pak 9,000 won MeatgreanonionJeon 8,000 won MeatMungbean pancake 8,'),
('Jokbal', 'During the Korean War, refugees flooded into Seoul, and there was a lot of vacant houses left by Japanese residents in Jangchung - dong area. After that, they sold food for their livelihood. The jokbal was very influenced by the pancreas. Although the fragrance was weakened during the process of Koreanization, it seems that the Chinese had a lot of influence because it was not uncommon to have strong Chinese octagonal jokbal until the 1980s.', 'It is similar to the festival food of Chinese festival, and it is guessed that the origin of jokbal is a food culture strongly influenced by Chinese civilization. In addition, there are also Brazilian, Japanese, Okinawan, Tebisobaba, German cuisine Schweinshaxe, Eisbein, Stelze in Austria, Koleno in Czech and Thai cuisine Kaoramu is also a dish using pig\'s legs. Irish cuisine also has a similar dish called Crubeens.', 'sam/jokbal/img(1).jpg', 'sam/jokbal/img(2).jpg', 'sam/jokbal/img(3).jpg', 'sam/jokbal/img(4).jpg', 'sam/jokbal/img(5).jpg', 'restaurants', 'halmaejib\r\n\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Jongro-gu</a> naejadong sajikro 12st. 1-5\r\nMenu Jokbal 30,000 won, 35,000 won\r\nTime 12:00 ~ 21:30\r\n<a href=\"h', 'ogane jogbal\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Gangnam-gu</a> Dogokdong 416-11\r\nMenu Jokbal 33,000won, 38,000won\r\nTime 10:00 ~ 22:00\r\n<a href=\"https://guid', 'manjog ohyang jogbal\r\nAddress Seoul-si<a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Jung-gu</a> Seosomun-ro 134-7\r\nMenu Jokbal 29,000won, 34,000won, 49,000won\r\nTime 11:30 ~ 22:30\r\n<a hr'),
('Naengmyeon', 'Origin of Naengmyeon comes from Pyongyang in the middle of the Goryeo Dynasty. According to a cookbook published in North Korea in 1973, Pyongyang Naengmyeon is said to have first emerged in the eastern part of Pyongyang\'s Daedong River district, and it is said that the noodles of buckwheat noodles were selected as noodles. There is a description of the intention that \'cold grain is eaten on the surface of the cold grain\' in the ancient literature that recorded Naengmyeon in the middle of the Goryeo period.', 'Naengmyeon are made with various kinds of flour such as kudzu, buckwheat, potato, sweet potato, and raw vegetables such as sun cucumber, pears, and meat and boiled eggs. Cold noodles are divided into water Naengmyeon and bibim Naengmyeon depending on the broth. Water Naengmyeon usually consist of the above ingredients in cold broth (usually beef broth). Water Naengmyeon can be subdivided according to the recipe of soup water or the type of meat or vegetables to be introduced.', 'sam/nangmun/img(1).jpg', 'sam/nangmun/img(2).jpg', 'sam/nangmun/img(3).jpg', 'sam/nangmun/img(4).jpg', 'sam/nangmun/img(5).jpg', 'restaurants', 'Pyeong-yangmyeon-og\r\n\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Jung-gu</a> Jangchoondan street 207\r\nMenu Naengmyun 11,000 won Pyeonyuk 25,000 won\r\nTime 11:00 - 21', 'Bongpiyang\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Yangjaedaero</a>71st. 1-4\r\nMenu Pyongyang Naengmyun 13,000 won Bibim Naengmyun 12,000 won\r\nTime 11:00 ~ 21:30\r', 'ulaeog\r\nAddress Seoul-si <a href=\"http://localhost/webapp2/sight.php?sight=sight.txt\">Jung-gu</a> Changkyunggungro 62-29\r\nMenu Pyongyang Naengmyun 12,000 won Bibim Naengmyun 12,000 won\r\nTime 11:30 ~ 2');

-- --------------------------------------------------------

--
-- 테이블 구조 `session`
--

CREATE TABLE `session` (
  `no` int(11) DEFAULT NULL,
  `user_id` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `session` char(30) CHARACTER SET utf8 DEFAULT NULL
);

-- --------------------------------------------------------

--
-- 테이블 구조 `sight`
--

CREATE TABLE `sight` (
  `sight_name` varchar(20) NOT NULL,
  `par1` varchar(3000) NOT NULL,
  `par2` varchar(3000) NOT NULL,
  `pic1` varchar(40) NOT NULL,
  `pic2` varchar(40) NOT NULL,
  `pic3` varchar(40) NOT NULL,
  `pic4` varchar(40) NOT NULL,
  `pic5` varchar(40) NOT NULL,
  `sight_time` varchar(150) NOT NULL,
  `amount` varchar(500) NOT NULL,
  `etc_data` varchar(150) NOT NULL
);

--
-- 테이블의 덤프 데이터 `sight`
--

INSERT INTO `sight` (`sight_name`, `par1`, `par2`, `pic1`, `pic2`, `pic3`, `pic4`, `pic5`, `sight_time`, `amount`, `etc_data`) VALUES
('경복궁', '경복궁(景福宮)은 대한민국 서울 세종로에 있는 조선 왕조의 법궁(法宮, 정궁)이다. 1395년(태조 4년)에 창건하였다. ‘경복(景福)’은 시경에 나오는 말로 왕과 그 자손, 온 백성들이 태평성대의 큰 복을 누리기를 축원한다는 의미이다. 풍수지리적으로도 백악산을 뒤로 하고 좌우에는 낙산과 인왕산으로 둘러싸여 있어 길지의 요건을 갖추고 있다. 1592년, 임진왜란으로 인해 불탄 이후 그 임무를 창덕궁에 넘겨주었다가 1865년(고종 2년)에 흥선대원군의 명으로 중건되었다. 일제 강점기에는 조선총독부 건물을 짓는 등 많은 전각들이 훼손되었으나, 1990년대부터 총독부 건물을 철거하는 등 복원사업을 벌인 덕분에 복원 작업은 현재 부분 완료된 상태다. 근정전, 경회루, 향원정, 아미산 굴뚝 등은 훼손되지 않고 그대로 남아 있다. 면적은 432,703m2이며, 동서 500m, 남북 700m 규모로 남아 있다.', '임진왜란 전 조선 전기 동안 조선 왕조의 법궁으로 중요한 역할을 담당했다. 경복궁은 법궁으로서의 권위를 나타내기 위해 엄정한 기하학적 공간분할, 반듯한 축선상의 건물배치, 정연한 대칭구조로 설계되었다. 이궁인 창덕궁(+창경궁)과 양궐 체제를 갖췄는데, 임금들은 각자의 취향에 따라 두 궁궐을 번갈아가며 사용했다. 임진왜란 때 소실된 이후 그 자리만 출입 금지로 묶인 채 200여 년 동안 재건되지 못하다가 흥선대원군이 재건해 지금까지 전해오고 있다. 참고로 흥선대원군이 지은 경복궁과 임진왜란 당시 소실된 경복궁의 모습은 당연히 차이가 있다.\n\"안일한 것을 경계하며 공경하고 두려워하는 마음을 두어서 황극의 복을 누리시면, 성자신손이 계승되어 천만대를 전하리이다.\"\n- 정도전, 조선왕조실록 태조 4년 10월 7일 ', 'contentsample_pic/gyungbok/img1.jpg', 'contentsample_pic/gyungbok/img2.jpg', 'contentsample_pic/gyungbok/img3.jpg', 'contentsample_pic/gyungbok/img4.jpg', 'contentsample_pic/gyungbok/img5.jpg', '정보\n관람 시간 :\n09:00~17:00', '관람요금\r\n내국인 (만25세~만64세) : 3,000원\r\n내국인 (~만24세, 만65세~) : 무료\r\n외국인 (만19세~만64세) : 3,000원\r\n외국인 (만7세~만18세) : 3,000원\r\n외국인 무료 대상 : 만 6세이하, 만 65세 이상\r\n그 외의 무료 입장 조건 : 한복 착용, 매월 마지막주 수요일에 방문, 군복을 입은 현역 군인\r\n', '관람정보\r\n휴궁일 : 1월1일 , 화요일'),
('광화문광장', '조선 태조는 1394년에 도읍을 한양으로 옮기고 정국인 경복궁을 지었다. 그리고 궁의 정문인 광화문 앞 길의 좌우에 의정부를 포함해 육조 관아 거리를 건설했다. 육조거리는 육조가 있던 거리로 현재 광화문 앞에서 광화문 사거리까지 이르는 조선의 주작대로이다. 태조 이성계가 조선을 건국하고 경복궁이 준공되던 해인 1395년에 조성되었다. 주작대로를 기준으로 동쪽에는 의정부, 이조, 한성부, 호조가 자리잡았고, 서쪽에는 예조, 중추부, 사헌부, 병조, 형조, 공조, 사역원이 자리하였다. 1592년 임진왜란시에 불타 없어졌으나 경복궁이 재건되지 않았으므로 관아거리로 이용되었다. 대원군이 경복궁을 복원할 때 삼군부가 예조 터에 자리하게 되었다', '2009년 그 길이 다시 열렸다. 광화문에서 청계광장에 이르는 광장이 들어선 것이다. 광장은 크게 \'광화문의 역사를 회복하는 광장\', \'육조거리의 풍경을 재현하는 광장\', \'한국의 대표 광장\', \'시민들이 참여하는 도시문화 광장\', \'도심 속의 광장\', \'청계천 연결부\'로 나뉜다. 역사성과 문화, 휴식의 공간으로서 기능을 갖는다. 특히 \'한국의 대표광장\'은 세종대왕 동상이 자리해 광장의 구심점 역할을 한다. 이순신 장군 동상 주변부인 \'도심 속의 광장\'도 시민들에게 사랑받는 공간이다. 뿐만 아니라 인공연못과 분수를 설치해 여름철에는 시민들의 더위를 식혀준다. 이밖에도 서울시의 상징인 해치 조형물과 잔디광장도 조성했다.', 'contentsample_pic/kwanghwa/img(1).jpg', 'contentsample_pic/kwanghwa/img(2).jpg', 'contentsample_pic/kwanghwa/img(3).jpg', 'contentsample_pic/kwanghwa/img(4).jpg', 'contentsample_pic/kwanghwa/img(5).jpg', '정보\r\n관람 시간\r\n제한 없음', '관람 요금\r\n무료', '기타정보\r\n주변의 지하철역 : 경복궁역 (3호선), 광화문역 (5호선)\r\n주변의 명소 : 대한민국 역사 박물관(100m 이내, 종로구), 광화문(100m 이내, 종로구), 조계사(500m 이내, 종로구)'),
('동대문', '興仁之門. 서울 성곽의 동쪽 문. 일명 동대문이다. 숭례문과 함께 서울 성곽을 대표하는 건축물이며, 2008년 숭례문이 불타면서 사대문 중 유일하게 조선시대에 지어진 그대로 남아있는 문이 되었다. 조선 태조 5년(1396)에 처음 지어졌으며, 이후 단종 1년(1453)과 고종 6년(1869)에 고쳐 지어서 현재의 모습으로 이어져 내려오고 있다. 흥인지문은 일대의 지반이 낮아 땅을 돋운 뒤 건설하여야 해서 1396년(태조 5년) 9월 다른 성문을 건립할 때에도 공사중이었으며, 1398년(태조 7년)에야 비로소 완성되었다. 이 때의 이름은 흥인문(興仁門)이었다. 1451년(문종 1년) 3월에는 도성을 고쳐 쌓은 뒤 기한이 남아, 흥인지문을 혜화문과 더불어 수리하였다. 같은 해 10월에 본격적인 흥인지문 정비를 시작하여, 1453년(단종 1년) 5월에 완료하였다.', '화강석으로 된 홍예문의 축석 위에 지어졌으며, 도성의 8개 성문 중 유일하게 옹성을 갖추고 있다. 앞면 5칸·옆면 2칸 규모의 2층 건물로, 지붕은 앞면에서 볼 때 사다리꼴모양을 한 우진각 지붕이다. 지붕 처마를 받치기 위해 장식하여 만든 공포가 기둥 위뿐만 아니라 기둥 사이에도 있는 다포 양식인데, 그 형태가 가늘고 약하며 지나치게 장식한 부분이 많아 조선 후기의 특징을 잘 나타내주고 있다. 또한 바깥쪽으로는 성문을 보호하고 튼튼히 지키기 위하여 반원 모양의 옹성(甕城)을 쌓았는데, 이는 적을 공격하기에 합리적으로 계획된 시설이라 할 수 있다. 숭례문이 조선 초기 양식으로 지어졌으나 흥인지문은 조선 후기 양식으로 되어 있어 서로 대비된다.', 'contentsample_pic/dongdaemoon/img(1).jpg', 'contentsample_pic/dongdaemoon/img(2).jpg', 'contentsample_pic/dongdaemoon/img(3).jpg', 'contentsample_pic/dongdaemoon/img(4).jpg', 'contentsample_pic/dongdaemoon/img(5).jpg', '정보\r\n관람 시간\r\n제한 없음', '관람 요금\r\n무료', '기타정보\r\n주변의 지하철역 : 동대문역 (1, 4호선)\r\n주변의 명소 : 한양도성박물관(200m이내, 종로구), 동대문 디자인 플라자(400m이내, 종로구), 이화 벽화마을(700m, 종로구)'),
('보신각', '보신각(普信閣)은 서울특별시 종로구 종로사거리에 있는 전통 한옥 누각이다. 보신각종을 걸어 놓기 위해 만든 것으로 정면 5칸, 측면 4칸의 구조로 되어 있다. 조선 태조 5년(1396) 창건했다가, 고종 6년(1869), 1979년 8월 재건했다. 보신각종은 본래 원각사에 있던 종으로 세조 때에 주조한 것인데 1536년(중종 31년)에 숭례문 안으로 옮겨 놓았다가 1597년(선조 30년) 명례동 고개로 옮겼던 것을 광해군 때 종각을 복구하면서 이전한 것이다. 그 후 조선 후기까지 4차례나 화재와 중건이 있다가 1895년(고종 32년)에 종각에 \'보신각\'이란 편액이 걸린 이후 종도 보신각종이라 부르게 되었다. 6·25전쟁으로 종각이 파손된 것을 1953년 중건하였다가 1980년 다시 2층 종루로 복원하였고, 원래의 종을 보존하기 위해 1985년에 새로운 종을 주조하여 지금에 이른다.', '.1980년 지금의 모습으로 복원된 보신각은 서울을 상징하는 대표적인 전통 목조 건물로 알려져 있다. 특히 매년 양력 12월 31일 밤 12시를 기해 보신각종을 33번 치는 제야의 종 타종행사는 대한민국의 가장 대표적인 새해맞이 행사이다. 매년 제야의 종 타종행사가 열릴 때마다 수많은 시민이 보신각 앞에 운집하는 것으로도 유명하다. 또한 보신각의 타종은 12월 31일 밤 이외에도 8월 15일 광복절, 3월 1일 삼일절 등 국경일 낮 12시에 기념 타종 행사를 갖고 있다.', 'contentsample_pic/bosingak/img(1).jpg', 'contentsample_pic/bosingak/img(2).jpg', 'contentsample_pic/bosingak/img(3).jpg', 'contentsample_pic/bosingak/img(4).jpg', 'contentsample_pic/bosingak/img(5).jpg', '정보\r\n관람 시간\r\n11:00-12:10', '관람 요금\r\n무료', '기타정보\r\n휴무일 : 기념일 타종행사시(3.1절, 광복절, 연말), 혹한기, 혹서기, 매주 월요일\r\n타종 체험 : 12:00-12:10 (내국인 예약 필요, 외국인은 즉석에서 가능)\r\n주변의 지하철역 : 종각역(1호선)'),
('인사동', '인사동은 조선왕조(1392-1910) 때부터 근 600년 동안 서울의 심장부다. 현재의 인사동길은 건축가 김진애의 설계로 2000년에 재조성된 것으로 돌걸상과 돌방석, \'남인사 물동이\', \'북인사 물길\' 등이 설치된 돌길 형태로 단장되었다. 서울의 주요 도심로와 연결돼 있고, 청계천, 경복궁 등 주요 관광명소와도 가깝다. 흥선대원군의 사저였던 운현궁을 시작으로 일제강점기 말부터 골동품 상점과 전통찻집, 한국대표기념품 샵 등이 밀집해 있는 인사동을 돌아보며 과거로의 시간여행한 기분을 느낄 수 있다. 또한 인사동은 외국인에게 인기가 있는 쇼핑 명소이자 매리의 골목(Mary\'s Alley)라고도 알려져 있다.', '인사동은 여전히 서울의 대표 거리다. 가장 많은 외국인 관광객이 찾는 거리이며, 스타벅스의 간판마저 한글로 바꿔버릴 만큼 그 전통이 깊숙이 뿌리 내린 동네이기도 하다. 인사동에는 일제강점기 말부터 골동품 상가가 밀집해 있었으니 골동품 거리의 역사만도 족히 반세기는 훌쩍 뛰어넘는다. 지금 인사동 거리는 골동품상을 중심으로 화랑과 갤러리가 다수 자리한다. 그리고 골목 안쪽으로는 전통 맛집들이 즐비하다. 근래에는 쌈지길과 가나아트스페이스, 경인미술관, 목인박물관, 아름다운 차박물관 등 신구의 명소들이 조화를 이루며 새로운 지형도를 그려가고 있다.', 'contentsample_pic/insa/img(1).jpg', 'contentsample_pic/insa/img(2).jpg', 'contentsample_pic/insa/img(3).jpg', 'contentsample_pic/insa/img(4).jpg', 'contentsample_pic/insa/img(5).jpg', '정보\r\n관람 시간\r\n없음', '관람 요금\r\n무료', '기타정보\r\n주변의 지하철역 : 종로3가역(5호선), 종각역(1호선)\r\n인사동의 볼거리 : 운현궁, 경인미술관, 목인박물관, 인사동 쌈지길, 박물관은 살아있다 인사동점'),
('종묘', '왕의 위패를 모시고 제를 올리는 종묘는 조선의 역사에 더없이 중요한 장소였다. 임금이 새로 왕위에 오르면 가장 먼저 종묘와 사직에 나아가 절을 하며 제사를 드렸다. 종묘는 단순한 구조의 재실을 길게 연결해 장엄한 엄숙미를 연출한다. 이곳에서 지내는 제사인 종묘대제(宗廟大祭)는 삼국 시대부터 있었던 국가적인 행사로 국가 통치 질서의 기본이었다. 종묘제례는 1975년 중요무형문화재 제56호로 지정되었고 유네스코는 1995년 종묘를 세계문화유산으로 지정하였다. 궁궐의 건축이 화려하다면 종묘는 조용하고 단아하다. 특히 맞배지붕이 보여주는 건축미는 빼어난 한국의 전통 건축기술을 자랑한다. 종묘는 신주를 모시고 제사를 지내는 정전, 조종과 자손이 길이 평안하라는 의미를 담고 있는 영녕전, 그리고 전사청, 어숙실, 향대청, 신당 등으로 이루어져 있다.', '중심 건물인 종묘 정전(宗廟 正殿)은 정면이 매우 길고 건물 앞마당과 일체를 이루는 모습을 보이고 있어 서양은 물론 동양에서도 유례를 찾아볼 수 없는 예외적인 건축물로 꼽힌다. 동아시아 유교 문화의 오랜 정신적 전통인 조상숭배 사상과 제사 의례를 바탕으로 왕실 주도 하에 엄격한 형식에 따라 지어졌으며 현재에도 조선 시대의 원형을 그대로 유지하고 있다. 오늘날까지도 종묘제례(宗廟祭禮)라 불리는 제사 의례가 행해지고 있으며 제사에는 종묘제례악(宗廟祭禮樂)의 음악과 춤이 동반된다. 종묘의 건축과 관리, 제례 봉행에 관한 모든 사항은 조선왕조의 공식 기록으로 상세히 남아있다.', 'contentsample_pic/jongmyo/img(1).jpg', 'contentsample_pic/jongmyo/img(2).jpg', 'contentsample_pic/jongmyo/img(3).jpg', 'contentsample_pic/jongmyo/img(4).jpg', 'contentsample_pic/jongmyo/img(5).jpg', '정보\r\n관람 시간\r\n02-05월 09:00-18:00\r\n06-08월 09:00-18:30\r\n09-10월 09:00-18:00\r\n11-01월 09:00-17:30', '관람 요금\r\n내국인(만 25-64세) : 1000원\r\n내국인 (~만24세, 만65세~) : 무료\r\n외국인(만 19세-64세) : 1000원\r\n외국인 (만7-18세) : 500원\r\n외국인 (~만 6세, 만 65세~) : 무료\r\n그 외의 무료 관람 대상 : 한복 착용, 매월 마지막주 수요일(문화가 있는날), 군복을 입은 현역 군인', '관람정보\r\n휴관일 : 화요일\r\n토요일 : 자유관람\r\n그 외의 요일 : 문화재 해설사와 함께하는 관람\r\n주변의 지하철역 : 종로 3가역 (1, 3, 5호선)\r\n'),
('창덕궁', '창덕궁(昌德宮)은 대한민국 서울특별시에 있는 조선 시대 궁궐로 동쪽으로 창경궁과 맞닿아 있다. 경복궁의 동쪽에 있어서 조선 시대에는 창경궁과 더불어 동궐(東闕)이라 불렀다. 창덕궁은 비교적 원형이 잘 보존되어 있는 중요한 고궁이며, 특히 창덕궁 후원은 한국의 유일한 궁궐후원이라는 점과 한국의 정원을 대표한다는 점에서 그 가치가 높다. 1997년에 유네스코가 지정한 세계문화유산으로 등록되었다.', '1404년 조선의 3대 임금인 태종이 한양 재천도를 위해 건립한 궁궐이다. 조선 전기에는 정궁 경복궁에 이은 제2의 궁궐 역할을 했으며, 조선 후기에는 정궁의 역할을 했다. 조선 전기에는 경복궁과 양궐 체제를 이루었고, 조선 후기에는 경희궁과 양궐 체제를 이루었다. 조선시대 임금들은 이러한 양궐 체제의 두 궁 사이를 오가며 번갈아가며 거주한 경우가 많았다. 때문에 조선 전기에도 경복궁 못지 않게 쓰임새가 잦았던 궁궐이었고, 반대로 조선 후기에는 임금들이 경희궁을 이용하느라 창덕궁을 떠나 있었을 때도 많았다. 1868년 경복궁이 중건되면서 정궁의 지위를 상실했지만, 1907년 순종이 이어하면서 다시 황궁이 되어 마지막 순간의 대한제국과 그 운명을 함께 했던 궁궐이다.', 'contentsample_pic/changduck/img(1).jpg', 'contentsample_pic/changduck/img(2).jpg', 'contentsample_pic/changduck/img(3).jpg', 'contentsample_pic/changduck/img(4).jpg', 'contentsample_pic/changduck/img(5).jpg', '정보\r\n관람시간\r\n하절기 : 09:00-18:00\r\n동절기 : 09:00-17:30', '관람요금\r\n내국인 (만25세~만64세) : 3,000원\r\n내국인 (~만24세, 만65세~) : 무료\r\n외국인 (만19세~만64세) : 3,000원\r\n외국인 (만7세~만18세) : 3,000원\r\n외국인 무료 대상 : 만 6세이하, 만 65세 이상\r\n그 외의 무료 입장 조건 : 한복 착용, 매월 마지막주 수요일(문화가 있는날), 군복을 입은 현역 군인', '기타정보\r\n휴궁일 : 월요일\r\n후원 : 해설사 동행(70분 정도 소요)\r\n주변의 명소 : 창경궁(100m이내, 종로구), 종묘(100m 이내, 종로구), 대학로 (200m이내, 종로구)'),
('청계천', '본래 자연하천이었던 청계천은 조선 태종때 부터 한양을 서울로 한 조선시대 내내 개거, 준설 등 치수 사업의 대상이었다. 태종은 1406년부터 자연상태에 있었던 하천의 바닥을 쳐내서 넓히고, 양안에 둑을 쌓았으나 큰비가 올 때마다 피해는 계속되었다. 청계천의 양안을 돌로 쌓고, 광통교, 혜정교 등의 다리를 돌로 만들었다. \'개천(開川)\'이라는 말은 \'내를 파내다\'라는 의미로 자연상태의 하천을 정비하는 토목공사의 이름이었는데, 이 때의 개천 공사를 계기로 지금의 청계천을 가리키는 이름이 되었다.', '종로구와 중구 사이를 가르는 10.84km의 하천. 청계천의 짧은 정의다. 2003년 7월부터 2005년 9월까지 청계천은 엄청난 변신을 도모했다. 복개한 청계천로와 삼일로 주변 5.84km 구간을 복원하고 총 22개의 다리를 설치하는 등 시민들의 쉼터로 탈바꿈한 대대적인 공사였다. 청계광장을 중심으로 각종 문화행사 등이 열리면서 지금은 광장의 역할도 하고 있다. 최근에는 예술 공간으로서의 역할도 훌륭히 해내고 있다. 청계천의 변신은 여전히 현재진행형이다. 청계천은 스물두 개의 다리가 놓여있고 청계광장과 광통교, 정조반차도와 패션광장(문화의 벽), 청계천 빨래터와 소망의 벽, 존치교각(터널분수)과 버들습지 등은 청계천에서 놓쳐서는 안될 장소다.', 'contentsample_pic/chunggye/img(1).jpg', 'contentsample_pic/chunggye/img(2).jpg', 'contentsample_pic/chunggye/img(3).jpg', 'contentsample_pic/chunggye/img(4).jpg', 'contentsample_pic/chunggye/img(5).jpg', '정보\r\n관람 시간\r\n제한 없음', '관람 요금\r\n무료', '기타 정보\r\n주변의 지하철역 : 신설동역 (1, 2호선)\r\n주변의 명소 : 명동 (200m 이내, 중구), 인사동 (600m 이내, 종로구), 명동 성당(600m 이내, 중구)');

-- --------------------------------------------------------

--
-- 테이블 구조 `sight_en`
--

CREATE TABLE `sight_en` (
  `sight_name` varchar(20) NOT NULL,
  `par_en1` varchar(2000) NOT NULL,
  `par_en2` varchar(2000) NOT NULL,
  `pic1` varchar(60) NOT NULL,
  `pic2` varchar(60) NOT NULL,
  `pic3` varchar(60) NOT NULL,
  `pic4` varchar(60) NOT NULL,
  `pic5` varchar(60) NOT NULL,
  `sight_time_en` varchar(1500) NOT NULL,
  `amount_en` varchar(1500) NOT NULL,
  `extrinfo` varchar(1500) NOT NULL
);

--
-- 테이블의 덤프 데이터 `sight_en`
--

INSERT INTO `sight_en` (`sight_name`, `par_en1`, `par_en2`, `pic1`, `pic2`, `pic3`, `pic4`, `pic5`, `sight_time_en`, `amount_en`, `extrinfo`) VALUES
('Bosin-gak', 'Bosin-gak is a traditional Korean-style pavilion in the Jongno crossroad in Jongno-gu, Seoul. It is made for hanging various things, and has a structure of 5 front and 4 side. It was built in 1396 (5th year of Joseon Dynasty), and it was reconstructed in August, 1979, in the year of King Gojong (1869). It was originally a castle in the old fortress, and it was cast during the period of King Sejo. It was moved to Sungnyemun in 1536 (31st year of King Sejong) and moved to Yeongryeonggodong in 1597 (30th year of the ancestor). Thereafter, there were four fires and juniors until the late Joseon Dynasty. In 1895 (the 32nd year of King Gojong), it was called \"Bongsin-gak\". It was rebuilt in 1953, after the war broke out in the June 25 war, restored to the second floor bell tower in 1980, and in 1985, in order to preserve the original species', 'Bosin-gak, restored to its present shape in 1980, is known as a representative traditional wooden building symbolizing Seoul. Especially, it is the most popular New Year celebration event of the Republic of Korea in the year of 31st of December 31st of every year, and it is the most popular event of the Republic of Korea. Every year, when a variety of events are held every year, many citizens are famous for crowding in front of the temple. In addition to the night of December 31, Bosin-gak has a memorial event at 12 o\'clock on the day of independence, including August 15 Liberation Day and March 1 March.', 'contentsample_pic/bosingak/img(1).jpg', 'contentsample_pic/bosingak/img(2).jpg', 'contentsample_pic/bosingak/img(3).jpg', 'contentsample_pic/bosingak/img(4).jpg', 'contentsample_pic/bosingak/img(5).jpg', 'Information\r\nvisiting hours\r\n11:00~12:10 (1 hour 10min)', 'Admission fee\r\nFree', 'Additional Information\r\nClosed: At the time of the anniversary events (3.1, Independence Day, the end of the year), cold season, hot season, every Monday\r\nNearby Subway Station : Dongdaemun station (Line 1,4)\r\nNearby Attractions : Bell strike experience: 12: 00-12: 10 (Korean reservation required, foreigner available on the spot)\r\nNearby subway station: Jonggak Station (Line 1)\r\nNearby attractions: Cheonggyecheon (in 300m, Jongno-gu), Jogyesa (in 500m, Jongno-gu), Insadong (in 500m, Jongno-gu)'),
('Changdeokgung', 'Changdeok Palace is located on the eastern side of the Chosun Dynasty in Seoul, Korea. On the east side of Gyeongbokgung Palace, in the Joseon Dynasty, it was called Dongjung with Changgyeonggung. Changdeok Palace is an important palace with a relatively well-preserved original shape. Especially, Changdeok Palace is highly valued as Korea\'s only patronage and Korea\'s garden. It was registered as a UNESCO World Heritage Site in 1997.', 'It is a palace built in 1404 by Joseon\'s third king, Taejong, for Hanyang re-creation. In the Chosun dynasty, it served as the second palace after Gungbok Palace, and served as a shrine in the late Joseon Dynasty. During the Joseon Dynasty, Gyeongbok Palace and the Korean occupation system were established. The Chosun dynasties often lived alternately between the two palaces of the Korean system. Therefore, it was the palace which used as much as Gyeongbok Palace in the Joseon Dynasty. On the contrary, there were many wards in the late Joseon Dynasty when they were leaving Changdeok Palace using Gyeonghui Palace. In 1868, Gyeongbok Palace lost its status as a prime minister, but in 1907 it became the Imperial Palace, and it was the palace where the last moment of the Imperial Empire and its destiny were together.\r\n', 'contentsample_pic/changduck/img(1).jpg', 'contentsample_pic/changduck/img(2).jpg', 'contentsample_pic/changduck/img(3).jpg', 'contentsample_pic/changduck/img(4).jpg', 'contentsample_pic/changduck/img(5).jpg', 'Information\r\nvisiting hours\r\nSummer: 09: 00-18: 00\r\nWinter: 09: 00-17: 30', 'Admission fee\r\nKorean (25 ~ 64 years old): 3,000 won\r\nKorean (~ 24 years old, 65 years old ~): Free\r\nForeigner (ages 19 to 64): 3,000 won\r\nForeigner (age 7 ~ 18): 3,000 won', 'Additional Information\r\nFree for foreigners: 6 years of age or younger, 65 years of age or older\r\nOther free admission conditions: wearing hanbok, last Wednesday of the month (culture day), active soldier in uniform'),
('Cheonggyecheon', 'A 10.84km river between Jongno-gu and Jung-gu. It is a short definition of Cheonggyecheon Stream. However, many stories are concentrated in the Cheonggyecheon Stream. It is the history of 600 years of the capital city, and it is also a small memory of individual citizens of Seoul. From July 2003 to September 2005, the Cheonggyecheon was transformed into an enormous transformation. It was a large-scale construction to transform the Cheonggyecheon and Cheonggyecheon roads, which were completed in 5.84 km around Samilno, and to install a total of 22 bridges. Various cultural events such as Cheonggye Plaza have been held, and now they are also playing the role of the plaza. In recent years, it is also playing a role as an art space. You can meet the sensitivity of the artists in the creative stage like Gwanggyo Gallery and Cheonggye Creative Studio. On weekends, performances by street artists take place. The transformation of Cheonggyecheon is still in progress.', 'Cheonggyecheon has a 5.84km stretch of twenty-two bridges that reveal new scenery along the waterways. Cheonggye Plaza, Gwangtong Bridge, Jeongjo Street, Fashion Square (Cheonggyecheon Stream), Cheonggyecheon Washroom and Desire Wall, Mincheon Bridge (Tunnel Fountain) and Willow Wetland are not only excuses but also a place of particular significance.', 'contentsample_pic/chunggye/img(1).jpg', 'contentsample_pic/chunggye/img(2).jpg', 'contentsample_pic/chunggye/img(3).jpg', 'contentsample_pic/chunggye/img(4).jpg', 'contentsample_pic/chunggye/img(5).jpg', 'Information\r\nvisiting hours\r\nFree', 'Admission fee\r\nFree', 'Additional information\r\nNearby Subway Stations: Shinseok-dong (lines 1 and 2)\r\nNearby Attractions : Myeongdong (less than 200m, Jung-gu), Insadong (less than 600m, Jongno-gu), Myeongdong Cathedral (less than 600m)'),
('Dongdaemun', '興仁之門.East Gate of Seoul Castle. It is a Dongdaemun. It is the architecture that represents the Seoul castle together with Sungnyemun. In 2008, Sungnyemun was burned and it became the only gate of Sadeemun which was built in the Joseon Dynasty. It was first built in the 5th year of Chosun Taejo (1396) and after that it was revised in the year of ending (1453) and 6th year of King Gojong (1869). Heunginjimun was built under the construction of another gates in September, 1396 (5th year of King Taejo), and it was completed only after 1398 (7th year of King Taejo). The name of this time was Heungin-mun. In 1451 (Mongol 1 year), the city was repaired in March, and the time was left for repairing the Heunginjimun along with Hyehwamun. In October of the same year, he began full-scale heunginjimon maintenance and completed it in 1453 (one year of discontinuance) in May.', 'It is built on the granite stone of Hong Yeonmun and is the only one of the eight gates of the city. This is a 2-story building with 5 front and 2 side panels. The roof is a roof-shaped roof that is trapezoidal when viewed from the front. The horror that was decorated to support the eaves of the roof is a multi-paneled style, which is not only on the pillar but also between the pillars. Its shape is thin, weak, and overly decorated, which shows the characteristics of the late Joseon Dynasty. On the outer side, we built a semicircular castle to protect the gate and keep it strong, which is a reasonably planned facility to attack the enemy. Sungnyemun was built in early Joseon style, but Heunginjimun is in late Joseon style and contrasts.', 'contentsample_pic/dongdaemoon/img(1).jpg', 'contentsample_pic/dongdaemoon/img(2).jpg', 'contentsample_pic/dongdaemoon/img(3).jpg', 'contentsample_pic/dongdaemoon/img(4).jpg', 'contentsample_pic/dongdaemoon/img(5).jpg', 'Information\r\nvisiting hours\r\nFree', 'Admission fee\r\nFree', 'Additional information\r\nNearby Subway Station : Dongdaemun station (Line 1,4)\r\nNearby Attractions : Hanyang Provincial Museum(in 200m, Jongno-gu), Dongdaemun Design Plaza(in 400m,Jongno-gu), Ewha Mural Village(in 700m,Jongno-gu)'),
('Gwanghwamun-Square', 'The Chosun Taejo moved the city to Hanyang in 1394 and built Gyeongbokgung Palace. Then, on the left and right of the road in front of Gwanghwamun Gate, which is the front gate of the palace, we built the Hutongsuka Street including Uijeongbu. It is a street where there is a yogi, and it is the main street of Chosun from the front of Gwanghwamun to Gwanghwamun intersection. Taejo was founded in 1395 when Lee Sung-gye founded Korea and Kyeongbok Palace was completed. Uijeongbu, Jijo, Hanseongbu, and Hojo are located on the east side of Jogakjae street, and Yeongjo, Chungbu, Confederation, Byeongjo, Jungjo, Hwajo, and Ministries are located in the west. It was burned down in 1592 during the invasion of Imjin, but it was used as a street because it was not rebuilt. When Daewonun restored Gyeongbokgung Palace, the three army officers were located at Yesojeon.', 'In 2009 the road opened again. There is a square from Gwanghwamun to Cheonggye Plaza. The plaza is largely divided into four areas: the square restoring the history of Gwanghwamun, the square representing the scenery of Hwaseong street, the representative square of Korea, the city culture plaza in which citizens participate, the plaza in the city center, \'. It functions as a space for history, culture, and relaxation. In particular, \'Korea\'s Main Square\' is located in the statue of King Sejong the Great, and serves as the center of the square. The plaza in the city center, which is surrounded by the statue of Admiral Yi Sun-sin, is also a place that is loved by citizens. In addition, artificial ponds and fountains are installed to cool the heat of the citizens in the summer. In addition, the hatch artifacts and grass plaza, which are the symbols of Seoul, were also created.', 'contentsample_pic/kwanghwa/img(1).jpg', 'contentsample_pic/kwanghwa/img(2).jpg', 'contentsample_pic/kwanghwa/img(3).jpg', 'contentsample_pic/kwanghwa/img(4).jpg', 'contentsample_pic/kwanghwa/img(5).jpg', 'Information\r\nvisiting hours\r\nFree', 'Admission fee\r\nFree', 'Additional information\r\nNearby Subway Station : GyeongBogGung Station (Line 3), Gwanghwamun Station (Line 5)\r\nNearby Attractions : Korea History Museum(in 100m, Jongno-gu),Gwanghwamun(in 100m,Jongno-gu), Jogyesa(in 500m,Jongno-gu)'),
('Gyeongbokgung', 'The Gyeongbokgung Palace is the court palace of the Joseon Dynasty in Sejongno, Seoul, Korea. It was founded in 1395 (4th year of Taejo). \'Gyeongbok\' means that the king, his descendants, and all the people are blessed to enjoy the great blessings of Taipingong Sungdae. It is surrounded by Naksan and Inwangsan on the left and right. In 1592, after the disastrous burning of the Japanese invasion in 1592, he gave up the task to Changdeokgung. In 1865 (2nd year of King Gojong) In the period of Japanese occupation, many of the monuments were damaged such as the building of the Chosun governor\'s office. However, restoration work has already been completed thanks to the restoration project such as demolishing the governor\'s building from the 1990s. Kunjeongjeon, Gyeonghoeru, Taejwonjeong, Amisan chimneys remain untouched. The area is 432,703m2, and it is 500m east-west and 700m north-south.', 'The Imjin war in 1592 played an important role in the court of the Joseon Dynasty during the previous Joseon Dynasty. Gyeongbokgung has been designed with strict geometric spatial division, straight axis building arrangement, and square symmetrical structure to express authority as a courtroom.The Changdeokgung (+ Changgyeonggung) and Yanggyeonggwang system were used, and the wagons used the two palaces alternately according to their preference. After being destroyed during the Japanese invasion of Korea, it was tied up with a ban on the spot and rebuilt for 200 years. For reference, Gyeongbok Palace built by Heungseon Daewongun and Gyeongbokgung, which was destroyed during the Imjin War, differ naturally.\r\n\"If you take care of your anger, honor and fear, and enjoy the blessing of the ancestor, your saints will be succeeded and will proclaim ten million.\" - JeongDoeJeon, Chosun Dynasty Taejo 4th year, October\r\n', 'contentsample_pic/gyungbok/img1.jpg', 'contentsample_pic/gyungbok/img2.jpg', 'contentsample_pic/gyungbok/img3.jpg', 'contentsample_pic/gyungbok/img4.jpg', 'contentsample_pic/gyungbok/img5.jpg', 'Information\r\nvisiting hours\r\n09:00 ~ 17:00', 'Admission fee\r\nKorean (25 ~ 64 years old): 3,000 won\r\nKorean (~ 24 years old, 65 years old ~): Free\r\nForeigner (ages 19 to 64): 3,000 won\r\nForeigner (age 7 ~ 18): 3,000 won', 'Additional information\r\nFree for foreigners: 6 years of age or younger, 65 years of age or older\r\nOther free admission conditions: wearing hanbok, visiting last Wednesday of every month, active duty soldier in uniform'),
('Insa-dong', 'Insadong is the heart of Seoul for nearly 600 years since the Joseon Dynasty (1392-1910). The present Insadong road was rebuilt in 2000 with the design of architect Kim Jin-ae and was renovated in the shape of a stone pavement with stone stools, stone cushions, \"Bokseongsa Basin\" It is connected to the main city center of Seoul and is close to major tourist attractions such as Cheonggyecheon and Gyeongbokgung Palace. Insadong is also known as Mary\'s Alley, a popular shopping destination for foreigners.', 'Insadong is still the representative street of Seoul. It is a town where the most foreign tourists are looking for, and it is a town where the tradition has deeply rooted enough for the Starbucks signboard to turn into Hangul. In Insadong, antique shops were crowded from the end of Japanese colonial rule, so the history of antique streets goes well beyond half a century. There are a lot of galleries and galleries around Insa-dong Antique awards. And inside the alley are many traditional restaurants. In recent years, new sights such as Ssamzie Gil, Gana Art Space, Gyeongin Museum, Thorny Museum, and beautiful tea museum are drawing harmonious new topographic maps.\r\n', 'contentsample_pic/insa/img(1).jpg', 'contentsample_pic/insa/img(2).jpg', 'contentsample_pic/insa/img(3).jpg', 'contentsample_pic/insa/img(4).jpg', 'contentsample_pic/insa/img(5).jpg', 'Information\r\nvisiting hours\r\nFree', 'Admission fee\r\nFree', 'Additional Information\r\nNearby subway stations: Jongno 3-ga Station (Line 5), Jonggak Station (Line 1)\r\nThings to see in Insadong: Unhyeonggung, Gyeongin Museum, Sookmyung Museum, Insa-dong Ssamjilil, Museum is alive Insadong'),
('Jongmyo', 'The jongmyo that raised the prize with the king\'s tablets was an important place in the history of Joseon. When the king came to the new throne, he first went to the jongmyo and resigned and bowed down and offered sacrifices. Jongmyo is a majestic solemn beauty by connecting a long room of simple structure. The jongmyo grand festival is a national event that took place since the Three Kingdoms period and was the basis of the national governing order. Jongmyo ritual was designated as Important Intangible Cultural Property No. 56 in 1975 and UNESCO designated Jongmyo as a World Cultural Heritage in 1995. If the palace architecture is gorgeous, Jongmyo is quiet and simple. In particular, the roofs of the roofs of the roofs show the outstanding traditional architecture of Korea. Jongmyo is composed of blackout, ceremony, and Yongnengjeon, which means that the descendants should rest in peace.', 'The main building, Jongmyojungjeonjeon, has a very long front and forms an integral part of the front yard of the building, making it an exceptional building that can not be found in Western or Oriental buildings. It was built according to the strict style under the leadership of the royal family based on the ancient spiritual tradition of the East Asian Confucian culture and the ritual of the ancestor worship and still maintains the original form of the Joseon period. Even today, ceremonial rituals called Jongmyo Shrine are being held. The ceremony is accompanied by music and dance of Jongmyo Jyoteiak. All matters concerning the construction and management of the Jongmyo Shrine and the burial of the Jongmyo Shrine remain an official record of t he Joseon Dynasty.\r\n', 'contentsample_pic/jongmyo/img(1).jpg', 'contentsample_pic/jongmyo/img(2).jpg', 'contentsample_pic/jongmyo/img(3).jpg', 'contentsample_pic/jongmyo/img(4).jpg', 'contentsample_pic/jongmyo/img(5).jpg', 'Information\r\nvisiting hours\r\nFeb-May 09:00-18:00\r\nJun-Aug 09:00-18:30\r\nSep-Oct 09:00-18:00\r\nNov-Jan 09:00-17:30', 'Admission fee\r\nKorean (25-64 years old): 1,000 won\r\nKorean (~ 24 years old, 65 years old ~): Free\r\nForeigner (19-64 years old): 1,000 won\r\nForeigner (7-18 years old): 500 won\r\nForeigner (~ 6 years old, 65 years old ~): Free', 'Additional Information\r\nOther free visitors include: wearing hanbok, last Wednesday of each month (culture day), active duty soldier in uniform\r\nClosed: Tuesday\r\nSaturday: free viewing\r\nOther days: Viewing with cultural commentator\r\nNearby subway station: Jongno 3-ga station (lines 1, 3 and 5)');

-- --------------------------------------------------------

--
-- 테이블 구조 `user`
--

CREATE TABLE `user` (
  `no` int(11) NOT NULL,
  `user_id` varchar(15) CHARACTER SET utf8 NOT NULL,
  `user_pw` char(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 NOT NULL
);

--
-- 테이블의 덤프 데이터 `user`
--

INSERT INTO `user` (`no`, `user_id`, `user_pw`, `email`) VALUES
(25, 'guest4', '0c2f95edb0b3757e49dcf01e219914eb', 'hyanghope@naver.com'),
(24, 'guest3', '800e07f96539ce15ae6bf604dd9cc73f', 'hyanghope@naver.com'),
(23, 'guest2', 'c4e799fad53c0dec94d4f201a4dd5e78', 'hyanghope@naver.com'),
(22, 'guest1', '15dac3875ad0f994a832043be841dc7e', 'hyanghope@naver.com'),
(21, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'hyanghope@naver.com'),
(26, '1234', '81dc9bdb52d04dc20036dbd8313ed055', 'rewr@naver.com');

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `board`
--
ALTER TABLE `board`
  ADD PRIMARY KEY (`no`);

--
-- 테이블의 인덱스 `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`food_name`);

--
-- 테이블의 인덱스 `food_en`
--
ALTER TABLE `food_en`
  ADD PRIMARY KEY (`food_name`);

--
-- 테이블의 인덱스 `sight`
--
ALTER TABLE `sight`
  ADD PRIMARY KEY (`sight_name`);

--
-- 테이블의 인덱스 `sight_en`
--
ALTER TABLE `sight_en`
  ADD PRIMARY KEY (`sight_name`);

--
-- 테이블의 인덱스 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`no`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `board`
--
ALTER TABLE `board`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- 테이블의 AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
