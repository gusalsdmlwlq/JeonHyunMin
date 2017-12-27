using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class Sceneload : MonoBehaviour {

	GameObject gamemanager, game;
	public int scene;
	private int slimes;

	void Start () {
		Camera.main.aspect = 1024f / 1920f;
		gamemanager = GameObject.Find ("GameManager");
		game = GameObject.Find ("Background_Game");
		switch (scene) {
		case 1:
			break;
		case 2:
			List<string> clearstages = new List<string> ();
			clearstages = gamemanager.GetComponent<GameManager> ().Clearstages; //클리어한 스테이지 목록
			for (int i = 0; i < clearstages.Count; i++) { //클리어한 스테이지 색깔변경
				GameObject.Find (clearstages [i]).GetComponent<SpriteRenderer> ().color = new Color (0, 255, 255, 255);
			}
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("1-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("2-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("3-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("4-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("5-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("6-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("7-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("8-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			if (gamemanager.GetComponent<GameManager> ().Clearstages.Contains ("9-6"))
				GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			break;
		case 3:
			GameObject.Find ("Stage").GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/" + gamemanager.GetComponent<GameManager> ().stage, typeof(Sprite)); //현재 스테이지 이름 변경
			Slimes = gamemanager.GetComponent<GameManager> ().Slimes; //보유한 슬라임 갯수 변경
			if (gamemanager.GetComponent<GameManager> ().sound == false) {
				GameObject.Find ("soundbutton").GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/soundbutton(off)", typeof(Sprite));
			}
			if (gamemanager.GetComponent<GameManager> ().Bgm == false) {
				GameObject.Find ("bgmbutton").GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bgmbutton(off)", typeof(Sprite));
			}
			for (int i = 0; i < 3; i++) { //장착중인 아이템 불러오기
				GameObject.Find ("item-" + (i + 1)).GetComponent<Item> ().Item_ = gamemanager.GetComponent<GameManager> ().equipment [i];
			}
			break;
		case 4:
			gamemanager.GetComponent<GameManager> ().pause = false;
			GameObject.Find ("Stage").GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/" + gamemanager.GetComponent<GameManager> ().stage, typeof(Sprite));
			game.GetComponent<Game> ().stage = gamemanager.GetComponent<GameManager> ().stage; //스테이지 선택 화면에서 받은 레벨-스테이지를 메인게임에 전달
			game.GetComponent<Game> ().Score = 0; //스코어 초기화
			game.GetComponent<Game> ().Combo = 0;
			gamemanager.GetComponent<GameManager>().isitem_4 = false;
			gamemanager.GetComponent<GameManager>().isitem_5 = false;
			if (gamemanager.GetComponent<GameManager> ().stage.Split ('-') [1] != "6") { //보스스테이지는 아이템 적용x
				for (int i = 0; i < 3; i++) {
					if (gamemanager.GetComponent<GameManager> ().equipment [i] == "item_1") { //item_1(체력감소량 -30%)
						game.GetComponent<Game> ().hpheal = 0.1f;
					}
					if (gamemanager.GetComponent<GameManager> ().equipment [i] == "item_2") { //item_2(체력 +50)
						game.GetComponent<Game> ().hpplus = 10;
					}
					if (gamemanager.GetComponent<GameManager> ().equipment [i] == "item_3") { //item_3(슬라임점수 +30)
						game.GetComponent<Game> ().scoreplus = 10;
					}
					if (gamemanager.GetComponent<GameManager> ().equipment [i] == "item_4") { //item_4(변이슬라임 터뜨리면 10%로 체력10회복)
						gamemanager.GetComponent<GameManager>().isitem_4 = true;
					}
					if (gamemanager.GetComponent<GameManager> ().equipment [i] == "item_5") { //item_4(변이슬라임 터뜨리면 10%로 체력10회복)
						gamemanager.GetComponent<GameManager>().isitem_5 = true;
					}
				}
			} else { //보스스테이지는 체력 300고정
				game.GetComponent<Game> ().hpplus = 200;
			}
			game.GetComponent<Game> ().StartGame (); //게임 시작
			break;
		}
	}

	void Update () {
	
	}

	public int Slimes //슬라임 갯수가 변하면 텍스트 표시
	{
		get {
			return slimes;
		}
		set {
			slimes = value;
			gamemanager.GetComponent<GameManager> ().Slimes = slimes;
			GameObject.Find ("Slimes").GetComponent<TextMesh> ().text = "" + slimes;
			return;
		}
	}
}
