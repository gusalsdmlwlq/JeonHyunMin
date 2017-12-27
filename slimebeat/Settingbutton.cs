using UnityEngine;
using System.Collections;

public class Settingbutton : MonoBehaviour {

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
		case "settingbutton": //환경 설정 버튼
			if (GameObject.Find ("setting").transform.position.z == 2) {
				StartCoroutine ("Front"); //환경설정 창 표시
			}
			break;
		case "soundbutton":
			if (gamemanager.GetComponent<GameManager> ().sound) {
				gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/soundbutton(off)", typeof(Sprite));
				gamemanager.GetComponent<GameManager> ().sound = false; //게임매니저에 전달만 하고 소리 관리는 게임매니저에서
				PlayerPrefs.SetString("sound","false"); //효과음 on/off 변동시 저장
				PlayerPrefs.Save();
			} else {
				gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/soundbutton", typeof(Sprite));
				gamemanager.GetComponent<GameManager> ().sound = true;
				PlayerPrefs.SetString ("sound", "true");
				PlayerPrefs.Save();
			}
			break;
		case "bgmbutton":
			if (gamemanager.GetComponent<GameManager> ().Bgm) {
				gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bgmbutton(off)", typeof(Sprite));
				gamemanager.GetComponent<GameManager> ().Bgm = false;
				PlayerPrefs.SetString ("bgm", "false"); //bgm on/off 변동시 저장
				PlayerPrefs.Save();
			} else {
				gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bgmbutton", typeof(Sprite));
				gamemanager.GetComponent<GameManager> ().Bgm = true;
				PlayerPrefs.SetString ("bgm", "true");
				PlayerPrefs.Save();
			}
			break;
		case "setting": //Back 버튼
			StartCoroutine("Back"); //환경설정 창 제거
			break;
		}
		GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("button");
	}

	IEnumerator Front() //환경설정 창이 사라지고 나서도 터치가 계속 인식되는 버그 때문에 코루틴으로 잠깐 텀을 둠
	{
		yield return new WaitForEndOfFrame ();
		GameObject.Find ("setting").transform.Translate (new Vector3 (0, 0, -4));
	}
	IEnumerator Back()
	{
		yield return new WaitForEndOfFrame ();
		gameObject.transform.Translate (new Vector3 (0, 0, 4));
	}
}
