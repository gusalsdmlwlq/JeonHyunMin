using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class Background_Start : MonoBehaviour {

	GameObject  gamemanager;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		gamemanager.GetComponent<GameManager> ().State = "Start";
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
		SceneManager.LoadSceneAsync ("2");
		gamemanager.GetComponent<GameManager> ().State = "Stage"; //스테이지 선택 화면으로 카메라 이동
	}
}
