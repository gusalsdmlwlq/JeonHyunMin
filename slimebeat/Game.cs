using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class Game : MonoBehaviour {

	GameObject gamemanager, hpbar, slime, clearscore;
	public float hp, hpmax, speed, term;
	private int score=0, clear, ins, combo;
	public string stage;
	public int doubleslime; //슬라임 돌연변이 확률(0:0%, 1:10%, 2:20%..)
	public float hpheal=0; //아이템으로 감소하는 초당체력감소
	public float hpplus=0; //아이템으로 늘어나는 최대체력
	public int scoreplus=0; //아이템으로 늘어나는 슬라임 점수
	bool combo50=false;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		hpbar = GameObject.Find ("Hp");
	}

	void Update () {
		
	}

	IEnumerator Hp() //게임 시작시 시간이 지날수록 체력 감소
	{
		yield return new WaitForSeconds (3f); //대기 시간
		while (hp >= 0) {
			if (!GameObject.Find ("GameManager").GetComponent<GameManager> ().pause) {
				hp -= Time.deltaTime * 5 * (1-hpheal);
				hpbar.transform.localScale = new Vector3 (hp / hpmax, 1, 1); //체력바 길이 조정
			}
			yield return new WaitForEndOfFrame();
		} //체력 0됨
		gamemanager.GetComponent<GameManager> ().pause = true; //게임 중지
		hpbar.transform.localScale = new Vector3 (0, 1, 1); //체력바 길이 0으로 고정
		gamemanager.GetComponent<GameManager> ().Score = score; //게임매니저에 해당 스테이지 점수 전달
		Instantiate(Resources.Load("Prefabs/result"),new Vector3(transform.position.x,0,-1),transform.rotation); //결과창
		gamemanager.GetComponent<GameManager>().isresult = true;
		GameObject.Find ("result_score").GetComponent<TextMesh> ().text = "" + score; //점수 표시
		GameObject.Find ("result_highscore").GetComponent<TextMesh>().text = ""+gamemanager.GetComponent<GameManager>().highscore[stage];
		if (score >= clear) { //클리어
			Instantiate (Resources.Load ("Prefabs/clear"), new Vector3 (0, 5, -3), transform.rotation);
			List<string> inslist = gamemanager.GetComponent<GameManager> ().Clearstages; //clearstages.Add()는 Clearstages프로퍼티 set이 인식못함
			if (stage == "1-6" || stage == "2-6" || stage == "3-6") {
				//보스스테이지 첫 클리어시 아이템 지급
				if (!inslist.Contains (stage) && gamemanager.GetComponent<GameManager> ().Items.Count < 5) {
					string insitem = "";
					while (true) { //중복 방지
						insitem = "item_" + Random.Range (1, 4); //item_1 ~ item_3 랜덤
						if (!gamemanager.GetComponent<GameManager> ().Items.Contains (insitem))
							break;
					}
					List<string> getitem = gamemanager.GetComponent<GameManager> ().Items;
					getitem.Add (insitem);
					gamemanager.GetComponent<GameManager> ().Items = getitem;
					Instantiate (Resources.Load ("Prefabs/getitem"), new Vector3 (0, 0, -4), transform.rotation);
					GameObject.Find ("getitem").GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/" + insitem, typeof(Sprite));
				}
			}
			else if (stage == "4-6" || stage == "5-6" || stage == "6-6" || stage == "7-6" || stage == "8-6" || stage == "9-6") {
				//보스스테이지 첫 클리어시 아이템 지급
				if (!inslist.Contains (stage) && gamemanager.GetComponent<GameManager> ().Items.Count < 5) {
					string insitem = "";
					while (true) { //중복 방지
						insitem = "item_" + Random.Range (1, 6); //item_1 ~ item_5 랜덤
						if (!gamemanager.GetComponent<GameManager> ().Items.Contains (insitem))
							break;
					}
					List<string> getitem = gamemanager.GetComponent<GameManager> ().Items;
					getitem.Add (insitem);
					gamemanager.GetComponent<GameManager> ().Items = getitem;
					Instantiate (Resources.Load ("Prefabs/getitem"), new Vector3 (0, 0, -4), transform.rotation);
					GameObject.Find ("getitem").GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/" + insitem, typeof(Sprite));
				}
			}else if (stage == "10-6"/* && !inslist.Contains (stage)*/) { //all clear
				Debug.Log("df");
				Instantiate (Resources.Load ("Prefabs/allclear"), new Vector3 (0, 0, -5), transform.rotation);
			}
			inslist.Add (stage);
			gamemanager.GetComponent<GameManager> ().Clearstages = inslist;
		} else { //실패
			Instantiate (Resources.Load ("Prefabs/fail"), new Vector3 (0, 5, -3), transform.rotation);
		}
		gamemanager.GetComponent<AudioSource> ().Stop ();
		gamemanager.GetComponent<AudioSource> ().clip = gamemanager.GetComponent<GameManager> ().bgm_result;
		if(gamemanager.GetComponent<GameManager>().Bgm == true) gamemanager.GetComponent<AudioSource> ().Play ();
		Debug.Log (gamemanager.GetComponent<GameManager> ().slimetest);
	}

	public void StartGame() //스테이지 시작
	{
		gamemanager.GetComponent<GameManager> ().slimetest = 0;
		hpmax = 100f + hpplus; //최대 체력
		hp = hpmax;
		hpbar.transform.localScale = new Vector3 (1, 1, 1);
		StartCoroutine ("Hp"); //체력 감소 시작
		switch (stage) {
		case "1-1": //스테이지별로 다른 배열들을 만들어 일정 패턴을 반복
			speed = -6f; //스피드 설정
			term = 0.5f; //슬라임 생성 후 텀
			clear = 1100; //13
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 2, 0, 3, 0, 4, 0 });
			break;
		case "1-2":
			speed = -6f;
			term = 0.5f;
			clear = 1200; //13
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 4, 0, 2, 0, 3, 0 });
			break;
		case "1-3":
			speed = -6f;
			term = 0.5f;
			clear = 1300; //16
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 2, 0, 3, 4, 0, 4, 0, 3, 0, 2, 1, 0 });
			break;
		case "1-4":
			speed = -6f;
			term = 0.5f;
			clear = 1700; //20
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 2, 0, 3, 4, 0, 4, 3, 0, 2, 1, 0 });
			break;
		case "1-5":
			speed = -6f;
			term = 0.5f;
			clear = 1800; //20
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 0, 2, 3, 0, 3, 2, 0, 4, 1, 0 });
			break;
		case "1-6":
			speed = -7f;
			term = 0.5f;
			clear = 7500; //76-77
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 3, 2, 0, 2, 3, 4, 1, 0 });
			break;
		case "2-1":
			speed = -7f;
			term = 0.5f;
			clear = 2300; //24
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 2, 0, 4, 2, 3, 0 });
			break;
		case "2-2":
			speed = -7f;
			term = 0.5f;
			clear = 2400; //24
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 1, 0, 3, 2, 3, 0, 4, 1, 4, 0, 2, 3, 2, 0 });
			break;
		case "2-3":
			speed = -7f;
			term = 0.5f;
			clear = 2400; //25
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 2, 3, 0, 4, 3, 2, 1, 0 });
			break;
		case "2-4":
			speed = -7f;
			term = 0.5f;
			clear = 2500; //25
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 1, 0, 2, 4, 1, 3, 0, 4, 1, 4, 0, 3, 1, 4, 2, 0 });
			break;
		case "2-5":
			speed = -7f;
			term = 0.5f;
			clear = 2600; //27
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 4, 1, 0, 3, 2, 2, 3, 0 });
			break;
		case "2-6":
			speed = -7f;
			term = 0.4f;
			clear = 7500; //76
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 2, 3, 0, 4, 1, 2, 0, 1, 2, 3, 4, 0, 4, 0 });
			break;
		case "3-1":
			speed = -7f;
			term = 0.4f;
			clear = 3000; //30
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 2, 3, 1, 0, 4, 1, 3, 0, 2, 1, 4, 0, 3, 4, 2, 0 });
			break;
		case "3-2":
			speed = -7f;
			term = 0.4f;
			clear = 3000; //30
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 4, 0, 1, 1, 4, 0, 2, 3, 3, 2, 0 });
			break;
		case "3-3":
			speed = -7f;
			term = 0.4f;
			clear = 3200; //32
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 1, 4, 0, 2, 3, 2, 3, 0 });
			break;
		case "3-4":
			speed = -7f;
			term = 0.4f;
			clear = 3200; //32
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 2, 4, 3, 1, 0, 3, 1, 4, 2, 0 });
			break;
		case "3-5":
			speed = -7f;
			term = 0.4f;
			clear = 3500; //35
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 2, 3, 4, 0, 2, 1, 4, 2, 3, 0 });
			break;
		case "3-6":
			speed = -8f;
			term = 0.4f;
			clear = 11000; //112
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 2, 3, 4, 3, 2, 1, 0 });
			break;
		case "4-1":
			speed = -10f;
			term = 0.3f;
			clear = 1300; //13
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 0, 2, 0, 0, 3, 0, 0, 4, 0, 0 });
			break;
		case "4-2":
			speed = -10f;
			term = 0.3f;
			clear = 1600; //13
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 0, 3, 0, 0, 4, 0, 0, 2, 0, 0, 4, 0, 0, 1, 0, 0, 3, 0, 0 });
			break;
		case "4-3":
			speed = -10f;
			term = 0.3f;
			clear = 2200; //22
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 0, 0, 2, 4, 0, 0, 3, 2, 0, 0, 4, 1, 0, 0 });
			break;
		case "4-4":
			speed = -10f;
			term = 0.3f;
			clear = 2600; //22
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 4, 1, 0, 0, 3, 1, 0, 0, 2, 4, 0, 0 });
			break;
		case "4-5":
			speed = -10f;
			term = 0.3f;
			clear = 3000; //28
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 2, 0, 0, 3, 2, 4, 0, 0, 2, 4, 1, 0, 0, 4, 3, 1, 0, 0 });
			break;
		case "4-6":
			speed = -10f;
			term = 0.3f;
			clear = 8200; //80
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 3, 0, 2, 0, 4, 0, 2, 0, 1, 0, 1, 2, 3, 4, 0, });
			break;
		case "5-1":
			speed = -12f;
			term = 0.3f;
			clear = 2600; //22
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 4, 0, 3, 0, 2, 0, 4, 0 });
			break;
		case "5-2":
			speed = -12f;
			term = 0.3f;
			clear = 2600; //22
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 0, 0, 2, 4, 0, 0, 2, 1, 0, 0, 4, 3, 0, 0 });
			break;
		case "5-3":
			speed = -12f;
			term = 0.3f;
			clear = 3800; //32
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 1, 0, 3, 3, 0, 2, 2, 0, 4, 4, 0, 3, 3, 0, 2, 2, 0 });
			break;
		case "5-4":
			speed = -12f;
			term = 0.3f;
			clear = 3900; //32
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 0, 3, 1, 0, 2, 4, 0, 4, 2, 0, 3, 2, 0, 2, 3, 0 });
			break;
		case "5-5":
			speed = -12f;
			term = 0.3f;
			clear = 4000; //32
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 0, 2, 3, 0, 4, 3, 2, 0 });
			break;
		case "5-6":
			speed = -10f;
			term = 0.4f;
			clear = 14000; //141
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 10, 2, 4, 3, 20, 1, 4, 2, 30, 2, 1, 3, 40 });
			break;
		case "6-1":
			speed = -10f;
			term = 0.3f;
			clear = 3900; //32
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 0, 3, 20, 0, 4, 2, 0, 3, 10, 0 });
			break;
		case "6-2":
			speed = -10f;
			term = 0.3f;
			clear = 4200; //35
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 0, 4, 1, 0, 30, 2, 1, 0, 2, 4, 0, 3, 1, 0, 20, 3, 1, 0 });
			break;
		case "6-3":
			speed = -10f;
			term = 0.3f;
			clear = 4800; //40
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 4, 2, 3, 0, 10, 3, 2, 0, 4, 3, 1, 20, 0 });
			break;
		case "6-4":
			speed = -10f;
			term = 0.3f;
			clear = 6000; //42
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 2, 30, 2, 3, 0, 2, 2, 30, 3, 0 });
			break;
		case "6-5":
			speed = -10f;
			term = 0.3f;
			clear = 6200; //42
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 40, 1, 0, 1, 4, 1, 4, 0, 4, 4, 10, 1, 0, 4, 1, 1, 4, 0 });
			break;
		case "6-6":
			speed = -10f;
			term = 0.3f;
			clear = 14500; //140
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 1, 20, 1, 4, 0, 4, 2, 3, 40, 1, 2, 0 });
			break;
		case "7-1":
			speed = -10f;
			term = 0.2f;
			clear = 8600; //60
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 2, 3, 40, 0, 1, 2, 3, 4, 0 });
			break;
		case "7-2":
			speed = -10f;
			term = 0.2f;
			clear = 8800; //60
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 20, 3, 0, 4, 3, 1, 2, 0 });
			break;
		case "7-3":
			speed = -10f;
			term = 0.2f;
			clear = 8800; //60
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 2, 3, 20, 3, 0, 2, 2, 3, 3, 0, 3, 2, 30, 2, 0, 3, 3, 2, 2, 0 });
			break;
		case "7-4":
			speed = -10f;
			term = 0.2f;
			clear = 8800; //60
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 1, 40, 0, 1, 1, 4, 4, 0, 4, 1, 4, 10, 0, 4, 4, 1, 1, 0 });
			break;
		case "7-5":
			speed = -10f;
			term = 0.2f;
			clear = 8000; //56
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 2, 3, 1, 40, 2, 3, 0, 0, 4, 1, 3, 20, 4, 1, 0, 0 });
			break;
		case "7-6":
			speed = -10f;
			term = 0.3f;
			clear = 16000; //165
			doubleslime = 1;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 1, 4, 20, 3, 1, 4, 2, 40, 1, 3, 2, 4, 30, 0 });
			break;
		case "8-1":
			speed = -10f;
			term = 0.2f;
			clear = 7500; //48
			doubleslime = 2;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 20, 4, 0, 3, 2, 40, 1, 0 });
			break;
		case "8-2":
			speed = -10f;
			term = 0.2f;
			clear = 7800; //59
			doubleslime = 2;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 20, 3, 0, 3, 10, 2, 0, 4, 1, 30, 2, 0 });
			break;
		case "8-3":
			speed = -10f;
			term = 0.2f;
			clear = 7800; //59
			doubleslime = 2;
			StartCoroutine ("MakeSlime", new int[] { 1, 40, 1, 4, 0, 1, 10, 0, 4, 10, 4, 1, 0, 4, 40, 0 });
			break;
		case "8-4":
			speed = -10f;
			term = 0.15f;
			clear = 7000; //52
			doubleslime = 2;
			StartCoroutine ("MakeSlime", new int[] { 1, 40, 1, 4, 0, 0, 0, 1, 10, 4, 4, 0, 0, 0, 4, 10, 4, 1, 0, 0, 0, 4, 40, 1, 1, 0, 0, 0 });
			break;
		case "8-5":
			speed = -10f;
			term = 0.2f;
			clear = 8800; //66
			doubleslime = 2;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 40, 2, 4, 0, 2, 4, 10, 3, 4, 0 });
			break;
		case "8-6":
			speed = -10f;
			term = 0.2f;
			clear = 16000; //168
			doubleslime = 2;
			StartCoroutine ("MakeSlime", new int[] { 2, 4, 1, 30, 4, 2, 0, 0, 3, 1, 40, 2, 1, 3, 0, 0 });
			break;
		case "9-1":
			speed = -12f;
			term = 0.2f;
			clear = 6800; //46
			doubleslime = 3;
			StartCoroutine ("MakeSlime", new int[] { 1, 30, 0, 4, 20, 0, 3, 40, 0, 2, 10, 0 });
			break;
		case "9-2":
			speed = -12f;
			term = 0.2f;
			clear = 7800; //56
			doubleslime = 3;
			StartCoroutine ("MakeSlime", new int[] { 3, 20, 4, 0, 1, 30, 2, 0, 3, 10, 4, 0, 2, 40, 1, 0 });
			break;
		case "9-3":
			speed = -12f;
			term = 0.2f;
			clear = 8500; //63
			doubleslime = 3;
			StartCoroutine ("MakeSlime", new int[] { 1, 4, 10, 4, 0, 4, 1, 40, 1, 0, 1, 40, 1, 4, 0, 4, 10, 4, 1, 0 });
			break;
		case "9-4":
			speed = -12f;
			term = 0.2f;
			clear = 8800; //67
			doubleslime = 3;
			StartCoroutine ("MakeSlime", new int[] { 1, 2, 10, 4, 3, 0, 4, 3, 40, 1, 2, 0, 1, 2, 30, 4, 3, 0, 4, 3, 20, 1, 2, 0 });
			break;
		case "9-5":
			speed = -11f;
			term = 0.25f;
			clear = 7400; //52
			doubleslime = 3;
			StartCoroutine ("MakeSlime", new int[] { 10, 3, 2, 40, 0, 20, 4, 1, 30, 0, 40, 2, 3, 10, 0, 30, 1, 4, 20, 0 });
			break;
		case "9-6":
			speed = -14f;
			term = 0.6f;
			clear = 9000; //97
			doubleslime = 3;
			StartCoroutine ("MakeSlime", new int[] { 10, 30, 40, 20, 30, 40, 10, 20 });
			break;
		case "10-6":
			speed = -14f;
			term = 0.3f;
			clear = 18000; //187
			doubleslime = 0;
			StartCoroutine ("MakeSlime", new int[] { 1, 3, 4, 2, 4, 1, 3, 2, 4, 3, 1, 2 });
			break;
		}
	}

	IEnumerator MakeSlime(int[] line) //슬라임 생산
	{
		clearscore = Instantiate (Resources.Load ("Prefabs/clearscore"), new Vector3 (0, 0, -1), transform.rotation) as GameObject;
		clearscore.GetComponent<TextMesh> ().text = ""+clear;
		yield return new WaitForSeconds (3f); //대기 시간
		while (hp >= 0) {
			if (!gamemanager.GetComponent<GameManager> ().pause) {
				switch (line[0]) { //몇번째 라인에 슬라임을 만들지 지정
				case 0: //0은 슬라임을 만들지 않고 한 번 쉼(난이도 조절)
					ins = line [0]; //line의 첫번째 수를 마지막 자리로 보내기(셔플)
					for (int i = 0; i < line.Length; i++) {
						if (i == line.Length - 1) {
							line [i] = ins;
						} else {
							line [i] = line [i + 1];
						}
					}
					yield return new WaitForSeconds (term);
					break;
				default:
					switch (Random.Range (0, 4)) { //슬라임 색깔 랜덤으로 지정
					case 0:
						slime = Instantiate (Resources.Load ("Prefabs/r"), transform.position, transform.rotation) as GameObject;
						break;
					case 1:
						slime = Instantiate (Resources.Load ("Prefabs/b"), transform.position, transform.rotation) as GameObject;
						break;
					case 2:
						slime = Instantiate (Resources.Load ("Prefabs/g"), transform.position, transform.rotation) as GameObject;
						break;
					case 3:
						slime = Instantiate (Resources.Load ("Prefabs/y"), transform.position, transform.rotation) as GameObject;
						break;
					}
					slime.transform.parent = gameObject.transform; //슬라임 위치 지정
					if (line [0] < 10)
						slime.transform.localPosition = new Vector3 (-6.4f + line [0] * 2.56f, 5.2f, -1);
					else {
						slime.transform.localPosition = new Vector3 (-6.4f + (line[0]/10) * 2.56f, 5.2f, -1);
						slime.GetComponent<Slime> ().isdouble = true;
					}
					ins = line [0]; //셔플
					for (int i = 0; i < line.Length; i++) {
						if (i == line.Length - 1) {
							line [i] = ins;
						} else {
							line [i] = line [i + 1];
						}
					}
					yield return new WaitForEndOfFrame();
					break;
				}
				yield return new WaitForSeconds(term); //슬라임을 만들고 쉬는 텀
			}
			yield return new WaitForEndOfFrame ();
		}
	}

	public int Score //점수 표시
	{
		get {
			return score;
		}
		set {
			score = value;
			GameObject.Find ("Score").GetComponent<TextMesh> ().text = ""+score;
		}
	}

	public int Combo
	{
		get {
			return combo;
		}
		set {
			combo = value;
			if (combo == 50 && combo50 == false) {
				Instantiate (Resources.Load ("Prefabs/50combo"), new Vector3 (0, 5, -2), transform.rotation);
				Score += 1000;
				if (gamemanager.GetComponent<GameManager> ().isitem_5 == true)
					Score += 500;
				combo50 = true;
			}
		}
	}
}
