using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class Stagebutton : MonoBehaviour {

	GameObject gamemanager;
	public string stage, prestage;

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
						StartCoroutine ("touching");
					}
				}
			} 
		}
	}

	IEnumerator touching()
	{
		yield return new WaitForEndOfFrame ();
		Touched ();
	}

	void Touched()
	{
		GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
		switch (gameObject.name) {
		case "stage_down":
			GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, 19.2f, 0));
			break;
		case "stage_up":
			GameObject.Find ("Background_Stage").transform.Translate (new Vector3 (0, -19.2f, 0));
			break;
		default:
			if (stage != "1-1") {
				if (!gamemanager.GetComponent<GameManager> ().Clearstages.Contains (prestage)) {
					return;
				}
			}
			gamemanager.GetComponent<GameManager> ().stage = stage; //선택한 레벨-스테이지를 게임매니저에 전달
			SceneManager.LoadSceneAsync ("3");
			gamemanager.GetComponent<GameManager> ().State = "Gamestart";
			break;
		}
	}
}
