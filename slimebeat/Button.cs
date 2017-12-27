using UnityEngine;
using System.Collections;

public class Button : MonoBehaviour {

	GameObject gamemanager, beatslime, line, red, blue, green, yellow;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		red = GameObject.Find ("redbutton");
		blue = GameObject.Find ("bluebutton");
		green = GameObject.Find ("greenbutton");
		yellow = GameObject.Find ("yellowbutton");
	}

	void Update () {
		if (Input.touchCount > 0) { //터치입력
			if (Input.touchCount == 1) { //터치가 하나일 때
				if (Input.GetTouch (0).phase == TouchPhase.Began) { //처음 눌린 상태인지 검사
					Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
					Vector2 touchpos = new Vector2 (pos.x, pos.y);
					//돌연변이 슬라임 처리(돌연변이 슬라임과 일반 슬라임의 터치처리를 나눔)
					if (gamemanager.GetComponent<GameManager> ().slimelist.Count > 0 &&
					    gamemanager.GetComponent<GameManager> ().slimelist [0].GetComponent<Slime> ().doubleslime == true) { //가장 아래의 슬라임이 돌연변이인 경우
						if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
							gamemanager.GetComponent<GameManager> ().Touchs = null;
							gamemanager.GetComponent<GameManager> ().Touchs += gameObject.name; //터치한 버튼의 색깔을 전달
						}
					}
					//
					else if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
						Touched (); //터치확인
					}
				}
			} else if (Input.touchCount == 2) { //터치가 두개일 때
				if (Input.GetTouch (0).phase == TouchPhase.Began && Input.GetTouch (1).phase != TouchPhase.Began) { //연속터치
					Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
					Vector2 touchpos = new Vector2 (pos.x, pos.y);
					//
					if (gamemanager.GetComponent<GameManager> ().slimelist.Count > 0 &&
						gamemanager.GetComponent<GameManager> ().slimelist [0].GetComponent<Slime> ().doubleslime == true) {
						if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
							gamemanager.GetComponent<GameManager> ().Touchs += gameObject.name; //터치한 버튼의 색깔을 전달
						}
					}
					//
					else if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
						Touched ();
					}
				} else if (Input.GetTouch (0).phase != TouchPhase.Began && Input.GetTouch (1).phase == TouchPhase.Began) {
					Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (1).position);
					Vector2 touchpos = new Vector2 (pos.x, pos.y);
					//
					if (gamemanager.GetComponent<GameManager> ().slimelist.Count > 0 &&
					    gamemanager.GetComponent<GameManager> ().slimelist [0].GetComponent<Slime> ().doubleslime == true) {
						if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
							gamemanager.GetComponent<GameManager> ().Touchs += gameObject.name; //터치한 버튼의 색깔을 전달
						}
					}
					//
					else if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
						Touched ();
					}
				} else if (Input.GetTouch (0).phase == TouchPhase.Began && Input.GetTouch (1).phase == TouchPhase.Began) { //이중터치
					Vector3 pos0 = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
					Vector3 pos1 = Camera.main.ScreenToWorldPoint (Input.GetTouch (1).position);
					Vector2 touchpos0 = new Vector2 (pos0.x, pos0.y);
					Vector2 touchpos1 = new Vector2 (pos1.x, pos1.y);
					if (transform.parent.GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos0) || transform.parent.GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos1))
						return;
					if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos0) && GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos1))
						return;
					if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos0) || GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos1)) {
						gamemanager.GetComponent<GameManager> ().Touchs += gameObject.name; //터치한 버튼의 색깔을 전달
					}
				}
			}
		} else
			gamemanager.GetComponent<GameManager> ().Touchs = null; //터치를 떼면 전달된 터치한 버튼의 색깔을 초기화
	}

	void Touched()
	{
		if (!gamemanager.GetComponent<GameManager>().pause) {
			if (gamemanager.GetComponent<GameManager> ().slimelist.Count > 0) {
				beatslime = gamemanager.GetComponent<GameManager> ().slimelist [0];
				Instantiate (Resources.Load ("Prefabs/line"), new Vector3 (0, beatslime.transform.localPosition.y, 0), transform.rotation);
			} else {
				Instantiate (Resources.Load ("Prefabs/line"), new Vector3 (0, 0, 0), transform.rotation);
			}
			switch (GetComponent<SpriteRenderer> ().name) {
			case "redbutton":
				StartCoroutine ("Red");
				break;
			case "bluebutton":
				StartCoroutine ("Blue");
				break;
			case "greenbutton":
				StartCoroutine ("Green");
				break;
			case "yellowbutton":
				StartCoroutine ("Yellow");
				break;
			}
			if (gamemanager.GetComponent<GameManager> ().sound == true) {
				GetComponent<AudioSource> ().Play ();
			}
		}
	}

	IEnumerator Red()
	{
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton_", typeof(Sprite)); //이미지 변경(눌린 상태)
		if (beatslime != null && beatslime.name == "r(Clone)") {
			beatslime.GetComponent<Slime> ().isbeat = true;
			Destroy (beatslime);
			GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
			gamemanager.GetComponent<GameManager> ().Slimepoint++;
		}
		if (beatslime != null && beatslime.name != "r(Clone)") {
			GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
			Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
		}
		yield return new WaitForSeconds(0.1f);
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton", typeof(Sprite));
	}
	IEnumerator Blue()
	{
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton_", typeof(Sprite));
		if (beatslime != null && beatslime.name == "b(Clone)") {
			beatslime.GetComponent<Slime> ().isbeat = true;
			Destroy (beatslime);
			GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
			gamemanager.GetComponent<GameManager> ().Slimepoint++;
		}
		if (beatslime != null && beatslime.name != "b(Clone)") {
			GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
			Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
		}
		yield return new WaitForSeconds(0.1f);
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton", typeof(Sprite));
	}
	IEnumerator Green()
	{
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton_", typeof(Sprite));
		if (beatslime != null && beatslime.name == "g(Clone)") {
			beatslime.GetComponent<Slime> ().isbeat = true;
			Destroy (beatslime);
			GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
			gamemanager.GetComponent<GameManager> ().Slimepoint++;
		}
		if (beatslime != null && beatslime.name != "g(Clone)") {
			GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
			Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
		}
		yield return new WaitForSeconds(0.1f);
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton", typeof(Sprite));
	}
	IEnumerator Yellow()
	{
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton_", typeof(Sprite));
		if (beatslime != null && beatslime.name == "y(Clone)") {
			beatslime.GetComponent<Slime> ().isbeat = true;
			Destroy (beatslime);
			GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
			gamemanager.GetComponent<GameManager> ().Slimepoint++;
		}
		if (beatslime != null && beatslime.name != "y(Clone)") {
			GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
			Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
		}
		yield return new WaitForSeconds(0.1f);
		gameObject.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton", typeof(Sprite));
	}

	IEnumerator Doubletouch(string colors) //이중터치
	{
		gamemanager.GetComponent<GameManager> ().Touchs = null; //게임매니저가 가진 터치한 버튼 색깔들을 초기화
		if (!gamemanager.GetComponent<GameManager>().pause) {
			if (gamemanager.GetComponent<GameManager> ().slimelist.Count > 0) {
				beatslime = gamemanager.GetComponent<GameManager> ().slimelist [0];
				Instantiate (Resources.Load ("Prefabs/line"), new Vector3 (0, beatslime.transform.localPosition.y, 0), transform.rotation);
			} else {
				Instantiate (Resources.Load ("Prefabs/line"), new Vector3 (0, 0, 0), transform.rotation);
			}
			switch (colors) { //터치한 버튼들의 색깔을 검사
			case "빨파":
				red.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton_", typeof(Sprite));
				blue.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton_", typeof(Sprite));
				if (beatslime != null && beatslime.name == "rb(Clone)") {
					beatslime.GetComponent<Slime> ().isbeat = true;
					Destroy (beatslime);
					GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
					gamemanager.GetComponent<GameManager> ().Slimepoint++;
					if (gamemanager.GetComponent<GameManager> ().isitem_4 == true && Random.Range (0, 10) < 1)
						GameObject.Find ("Background_Game").GetComponent<Game> ().hp += 10;
				}
				if (beatslime != null && beatslime.name != "rb(Clone)") {
					GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
					Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
				}
				yield return new WaitForSeconds (0.1f);
				red.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton", typeof(Sprite));
				blue.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton", typeof(Sprite));
				break;
			case "빨초":
				red.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton_", typeof(Sprite));
				green.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton_", typeof(Sprite));
				if (beatslime != null && beatslime.name == "rg(Clone)") {
					beatslime.GetComponent<Slime> ().isbeat = true;
					Destroy (beatslime);
					GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
					gamemanager.GetComponent<GameManager> ().Slimepoint++;
					if (gamemanager.GetComponent<GameManager> ().isitem_4 == true && Random.Range (0, 10) < 1)
						GameObject.Find ("Background_Game").GetComponent<Game> ().hp += 10;
				}
				if (beatslime != null && beatslime.name != "rg(Clone)") {
					GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
					Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
				}
				yield return new WaitForSeconds (0.1f);
				red.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton", typeof(Sprite));
				green.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton", typeof(Sprite));
				break;
			case "빨노":
				red.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton_", typeof(Sprite));
				yellow.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton_", typeof(Sprite));
				if (beatslime != null && beatslime.name == "ry(Clone)") {
					beatslime.GetComponent<Slime> ().isbeat = true;
					Destroy (beatslime);
					GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
					gamemanager.GetComponent<GameManager> ().Slimepoint++;
					if (gamemanager.GetComponent<GameManager> ().isitem_4 == true && Random.Range (0, 10) < 1)
						GameObject.Find ("Background_Game").GetComponent<Game> ().hp += 10;
				}
				if (beatslime != null && beatslime.name != "ry(Clone)") {
					GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
					Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
				}
				yield return new WaitForSeconds (0.1f);
				red.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/redbutton", typeof(Sprite));
				yellow.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton", typeof(Sprite));
				break;
			case "파초":
				blue.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton_", typeof(Sprite));
				green.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton_", typeof(Sprite));
				if (beatslime != null && beatslime.name == "bg(Clone)") {
					beatslime.GetComponent<Slime> ().isbeat = true;
					Destroy (beatslime);
					GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
					gamemanager.GetComponent<GameManager> ().Slimepoint++;
					if (gamemanager.GetComponent<GameManager> ().isitem_4 == true && Random.Range (0, 10) < 1)
						GameObject.Find ("Background_Game").GetComponent<Game> ().hp += 10;
				}
				if (beatslime != null && beatslime.name != "bg(Clone)") {
					GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
					Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
				}
				yield return new WaitForSeconds (0.1f);
				blue.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton", typeof(Sprite));
				green.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton", typeof(Sprite));
				break;
			case "파노":
				blue.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton_", typeof(Sprite));
				yellow.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton_", typeof(Sprite));
				if (beatslime != null && beatslime.name == "by(Clone)") {
					beatslime.GetComponent<Slime> ().isbeat = true;
					Destroy (beatslime);
					GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
					gamemanager.GetComponent<GameManager> ().Slimepoint++;
					if (gamemanager.GetComponent<GameManager> ().isitem_4 == true && Random.Range (0, 10) < 1)
						GameObject.Find ("Background_Game").GetComponent<Game> ().hp += 10;
				}
				if (beatslime != null && beatslime.name != "by(Clone)") {
					GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
					Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
				}
				yield return new WaitForSeconds (0.1f);
				blue.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/bluebutton", typeof(Sprite));
				yellow.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton", typeof(Sprite));
				break;
			case "초노":
				green.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton_", typeof(Sprite));
				yellow.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton_", typeof(Sprite));
				if (beatslime != null && beatslime.name == "gy(Clone)") {
					beatslime.GetComponent<Slime> ().isbeat = true;
					Destroy (beatslime);
					GameObject.Find ("Background_Game").GetComponent<Game> ().Score += 100 + GameObject.Find ("Background_Game").GetComponent<Game> ().scoreplus;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo++;
					gamemanager.GetComponent<GameManager> ().Slimepoint++;
					if (gamemanager.GetComponent<GameManager> ().isitem_4 == true && Random.Range (0, 10) < 1)
						GameObject.Find ("Background_Game").GetComponent<Game> ().hp += 10;
				}
				if (beatslime != null && beatslime.name != "gy(Clone)") {
					GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10f;
					GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
					Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
				}
				yield return new WaitForSeconds (0.1f);
				green.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/greenbutton", typeof(Sprite));
				yellow.GetComponent<SpriteRenderer> ().sprite = (Sprite)Resources.Load ("Images/yellowbutton", typeof(Sprite));
				break;
			}
			if (gamemanager.GetComponent<GameManager> ().sound == true) {
				GetComponent<AudioSource> ().Play ();
			}
		}
	}
}
