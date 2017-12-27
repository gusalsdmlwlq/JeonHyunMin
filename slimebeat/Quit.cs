using UnityEngine;
using System.Collections;

public class Quit : MonoBehaviour {

	void Start () {
	
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
		case "quit_ok": //종료
			Application.Quit ();
			break;
		case "quit_cancle": //종료 취소
			GameObject.Find ("GameManager").GetComponent<GameManager> ().quit = false;
			if (GameObject.Find ("GameManager").GetComponent<GameManager> ().isresult == false) {
				GameObject.Find ("GameManager").GetComponent<GameManager> ().pause = false;
			}
			Destroy (GameObject.Find ("quit(Clone)"));
			break;
		}
		GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
	}
}
