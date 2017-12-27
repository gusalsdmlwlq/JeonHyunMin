using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class GameManager : MonoBehaviour {

	private List<string> clearstages = new List<string>(); //클리어한 스테이지 목록
	public bool pause=false, sound=true, quit=false, isresult=false, isitem_4=false, isitem_5=false;
	private bool bgm=true;
	public GameObject soundmanager;
	private string state; //현재 게임의 상태(Start:시작화면,Stage:스테이지선택,Gamestart:게임시작,Game:게임중)
	private string touchs; //이중터치시 터치한 버튼 색깔들
	public string stage; //스테이지 선택 화면에서 레벨-스테이지를 전달 받음
	private int score; //게임종료후 얻은 점수
	private int slimes; //소유하고 있는 슬라임 갯수
	public List<GameObject> slimelist = new List<GameObject>(); //필드에 나온 슬라임들
	public Dictionary<string,int> highscore = new Dictionary<string,int>(); //스테이지별 최고기록
	public AudioClip bgm_start, bgm_stage, bgm_game, bgm_result, bgm_boss;
	private List<string> items = new List<string> (); //보유한 아이템 리스트
	public string[] equipment = new string[3]; //장착한 아이템
	private int slimepoint=0; //slimepoint 100 -> 슬라임 1개

	public int slimetest; //스테이지에서 만들어진 슬라임 갯수

	void Awake()
	{
		Screen.sleepTimeout = SleepTimeout.NeverSleep;
		Application.runInBackground = true;
		GameObject soundmanager = GameObject.Find ("SoundManager");
		DontDestroyOnLoad (gameObject);
		DontDestroyOnLoad (soundmanager);
		for (int i = 0; i < 3; i++) { //장착한 아이템 초기화
			equipment [i] = "noitem";
		}
	}

	void Start () {
	}

	void Update () {
		if (Application.platform == RuntimePlatform.Android) //뒤로가기 버튼
		{
			if(Input.GetKey(KeyCode.Escape)) //종료창 표시
			{
				StartCoroutine ("Quit");
			}
		}
	}

	public string State //현재 게임의 상태가 변경될 때 초기화 작업
	{
		get {
			return state;
		}
		set{
			state = value;
			switch (state) {
			case "Start": //게임 접속시 초기화
				GetComponent<AudioSource> ().Stop ();
				GetComponent<AudioSource> ().clip = bgm_start;
				GetComponent<AudioSource> ().Play ();
				break;
			case "Stage": //스테이지 선택 화면
				GetComponent<AudioSource> ().Stop ();
				GetComponent<AudioSource> ().clip = bgm_stage;
				GetComponent<AudioSource> ().Play ();
				break;
			case "Gamestart": //게임 시작 화면
				break;
			case "Game": // 게임 화면
				GetComponent<AudioSource> ().Stop ();
				GetComponent<AudioSource> ().clip = bgm_game;
				GetComponent<AudioSource> ().Play ();
				break;
			}
			return;
		}
	}

	public int Slimes //슬라임 갯수가 변하면 텍스트 표시
	{
		get {
			return slimes;
		}
		set {
			slimes = value;
			PlayerPrefs.SetInt ("slimes", slimes); //슬라임 갯수가 변하면 저장
			PlayerPrefs.Save ();
			return;
		}
	}

	public int Score
	{
		get {
			return score;
		}
		set {
			score = value;
			if (highscore.ContainsKey (stage)) { //스테이지 기록이 있는지 검사
				if (highscore [stage] < score) { //기록 갱신
					highscore [stage] = score;
					Instantiate (Resources.Load ("Prefabs/scorebreak"), new Vector3 (0, 0.5f, -3), transform.rotation);
					PlayerPrefs.SetInt (stage, score); //메인게임에서 하이스코어가 전달되면 하이스코어 목록에 저장
					PlayerPrefs.Save ();
				}
			} else { //기록이 없는 경우 새로 추가
				highscore.Add (stage, score);
				Instantiate (Resources.Load ("Prefabs/scorenew"), new Vector3 (0, 0.5f, -3), transform.rotation);
				PlayerPrefs.SetInt (stage, score);
				PlayerPrefs.Save ();
			}
		}
	}
		
	public void Load_highscores(string stage, int score) //하이스코어 불러와서 저장
	{
		highscore.Add (stage, score);
	}
		
	/*void OnApplicationFocus(bool isfocus)
	{
		if (!isfocus) { //어플도중 위 창 내리기,화면내리기
			StartCoroutine("Quit");
			return;
		} else { //어플 시작, 위창 올리기,화면올리기
			
		}
	}*/

	IEnumerator Quit() //뒤로가기를 1초동안 누르고 있으면 게임 종료
	{
		yield return new WaitForSeconds (0.5f);
		if (Application.platform == RuntimePlatform.Android)
		{
			if(Input.GetKey(KeyCode.Escape))
			{
				Application.Quit ();
			}
		}
	}

	public bool Bgm
	{
		get {
			return bgm;
		}
		set {
			bgm = value;
			if (bgm == false) { //bgm이 off되면 볼륨을 0으로
				GetComponent<AudioSource> ().volume = 0;
			} else {
				GetComponent<AudioSource> ().volume = 0.5f;
			}
		}
	}

	public string Touchs //이중터치
	{
		get {
			return touchs;
		}
		set {
			touchs = value;
			switch (touchs) { //이중터치시 터치한 두 버튼의 색깔을 검사
			case "redbuttonbluebutton":
			case "bluebuttonredbutton":
				GameObject.Find ("redbutton").GetComponent<Button> ().StartCoroutine ("Doubletouch", "빨파"); //빨간버튼이 대표로 한번만 실행
				break;
			case "redbuttongreenbutton":
			case "greenbuttonredbutton":
				GameObject.Find ("redbutton").GetComponent<Button> ().StartCoroutine ("Doubletouch", "빨초");
				break;
			case "redbuttonyellowbutton":
			case "yellowbuttonredbutton":
				GameObject.Find ("redbutton").GetComponent<Button> ().StartCoroutine ("Doubletouch", "빨노");
				break;
			case "bluebuttongreenbutton":
			case "greenbuttonbluebutton":
				GameObject.Find ("redbutton").GetComponent<Button> ().StartCoroutine ("Doubletouch", "파초");
				break;
			case "bluebuttonyellowbutton":
			case "yellowbuttonbluebutton":
				GameObject.Find ("redbutton").GetComponent<Button> ().StartCoroutine ("Doubletouch", "파노");
				break;
			case "greenbuttonyellowbutton":
			case "yellowbuttongreenbutton":
				GameObject.Find ("redbutton").GetComponent<Button> ().StartCoroutine ("Doubletouch", "초노");
				break;
			}
		}
	}

	public List<string> Clearstages //클리어한 스테이지들 불러오기
	{
		get {
			return clearstages;
		}
		set {
			clearstages = value;
			if (clearstages.Count > 0) {
				for (int i = 0; i < clearstages.Count; i++) { //clearstages에 저장된 스테이지들을 차례로 저장함(clearstages0,1,2...)
					PlayerPrefs.SetString ("clearstages" + i, clearstages [i]);
				}
				PlayerPrefs.Save ();
			}
		}
	}

	public List<string> Items
	{
		get {
			return items;
		}
		set { //보유한 아이템이 바뀌면 저장
			items = value;
			if (items.Count > 0) {
				for (int i = 0; i < items.Count; i++) {
					PlayerPrefs.SetString ("items" + i, items [i]);
				}
				PlayerPrefs.Save ();
			}
		}
	}

	public int Slimepoint
	{
		get {
			return slimepoint;
		}
		set {
			slimepoint = value;
			if (slimepoint >= 100) {
				slimepoint -= 100;
				Slimes++;
			}
			PlayerPrefs.SetInt ("slimepoint", slimepoint);
			PlayerPrefs.Save ();
		}
	}
}
