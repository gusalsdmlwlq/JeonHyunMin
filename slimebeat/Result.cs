using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class Result : MonoBehaviour {

	GameObject gamemanager, result;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		result = GameObject.Find ("result(Clone)");
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
		switch (gameObject.name) {
		case "retry": //재시도
			if (gamemanager.GetComponent<GameManager> ().Slimes > 0) {
				gamemanager.GetComponent<GameManager> ().Slimes--;
				gamemanager.GetComponent<GameManager> ().State = "Game";
				gamemanager.GetComponent<GameManager> ().isresult = false;
				Destroy (result);
				SceneManager.LoadSceneAsync ("4");
			} else {
				Instantiate (Resources.Load ("Prefabs/noslime"), new Vector3 (0, -0.75f, -4), transform.rotation);
			}
			break;
		case "stagechange": //스테이지 변경
			gamemanager.GetComponent<GameManager> ().isresult = false;
			Destroy (result);
			SceneManager.LoadSceneAsync ("2");
			gamemanager.GetComponent<GameManager> ().State = "Stage"; //스테이지 선택 화면으로 카메라 이동
			break;
		}
		GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
	}
}
