using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class Gamestart : MonoBehaviour {

	GameObject gamemanager;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
	}

	void Update () {
		if (Input.touchCount > 0) {
			if (Input.touchCount == 1) { //터치가 하나일 때
				if (Input.GetTouch (0).phase == TouchPhase.Began) { //처음 눌린 상태인지 검사
					Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
					Vector2 touchpos = new Vector2 (pos.x, pos.y);
					if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
						Touched (); //터치확인
					}
				}
			} 
		}
	}

	void Touched()
	{
		switch (gameObject.GetComponent<SpriteRenderer> ().name) {
		case "Start": //게임 시작
			if (gamemanager.GetComponent<GameManager> ().Slimes > 0) { //슬라임을 보유하고 있는지 검사
				gamemanager.GetComponent<GameManager> ().Slimes--;
				SceneManager.LoadSceneAsync ("4");
				gamemanager.GetComponent<GameManager> ().State = "Game";
			} else {
				Instantiate (Resources.Load ("Prefabs/noslime"), new Vector3 (0, -0.75f, -4), transform.rotation);
			}
			break;
		case "Back": //스테이지 선택 화면으로 이동
			SceneManager.LoadSceneAsync ("2");
			gamemanager.GetComponent<GameManager> ().State = "Stage";
			break;
		}
		GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
	}
}
