using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class GameLoader : MonoBehaviour {
//클리어한 스테이지, 보유한 슬라임 갯수, 보유한 아이템, 보유한 캡슐, 환경설정, 하이스코어

	GameObject gamemanager;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		Load_clearstages ();
		Load_slimes ();
		Load_setting ();
		Load_highscore ();
		Load_itemlist ();
		Load_equipment ();
		Load_slimepoint ();
	}

	void Update () {
	
	}

	void Load_clearstages() //클리어한 스테이지들 불러오기
	{
		List<string> inslist = gamemanager.GetComponent<GameManager> ().Clearstages; //임시 리스트 inlist
		int count = 0;
		while (PlayerPrefs.HasKey ("clearstages" + count)) { //"clearstages0, clearstages1...으로 저장된 값이 있는지 검사
			inslist.Add (PlayerPrefs.GetString("clearstages"+count)); //"clearstage0:1-1, clearstages1:1-2...
			count++;
		}
		gamemanager.GetComponent<GameManager> ().Clearstages = inslist;
	}

	void Load_slimes() //보유한 슬라임 갯수 불러오기
	{
		if (PlayerPrefs.HasKey ("slimes")) {
			gamemanager.GetComponent<GameManager> ().Slimes = PlayerPrefs.GetInt ("slimes");
		}
	}

	void Load_setting() //환경설정 설정값 불러오기
	{
		if (PlayerPrefs.HasKey ("bgm")) { //bgm on/off
			if (PlayerPrefs.GetString ("bgm") == "true") {
				gamemanager.GetComponent<GameManager> ().Bgm = true;
			} else {
				gamemanager.GetComponent<GameManager> ().Bgm = false; //불러올때 off상태면 버튼 이미지 변경
			}
		}
		if (PlayerPrefs.HasKey ("sound")) { //효과음 on/off
			if (PlayerPrefs.GetString ("sound") == "true") {
				gamemanager.GetComponent<GameManager> ().sound = true;
			} else {
				gamemanager.GetComponent<GameManager> ().sound = false;
			}
		}
	}

	void Load_highscore() //하이스코어 목록 불러오기
	{
		int level = 1, stage = 1;
		while (PlayerPrefs.HasKey (level + "-" + stage)) { //level-stage로 저장된 값이 있는지 검사
			gamemanager.GetComponent<GameManager> ().Load_highscores (level + "-" + stage, PlayerPrefs.GetInt (level + "-" + stage)); //하이스코어 목록에 스테이지-값 저장
			if (stage < 6) { //1-1 1-2 1-3 1-4 1-5 1-6순으로 올라감
				stage++;
			} else if (stage == 6) { // 1-6 다음은 2-1
				level++;
				stage = 1;
			}
		}
	}

	void Load_itemlist()
	{
		List<string> inslist = new List<string> ();
		int count = 0;
		while (PlayerPrefs.HasKey ("items" + count)) {
			inslist.Add (PlayerPrefs.GetString ("items" + count));
			count++;
		}
		gamemanager.GetComponent<GameManager> ().Items = inslist;
	}

	void Load_equipment()
	{
		for (int i = 0; i < 3; i++) {
			if (PlayerPrefs.HasKey ("equipment" + i)) {
				gamemanager.GetComponent<GameManager> ().equipment [i] = PlayerPrefs.GetString ("equipment" + i);
			}
		}
	}

	void Load_slimepoint()
	{
		if (PlayerPrefs.HasKey ("slimepoint")) {
			gamemanager.GetComponent<GameManager> ().Slimepoint = PlayerPrefs.GetInt ("slimepoint");
		}
	}
}
