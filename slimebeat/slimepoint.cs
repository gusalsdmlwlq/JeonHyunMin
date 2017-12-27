using UnityEngine;
using System.Collections;

public class slimepoint : MonoBehaviour {
	GameObject gamemanager;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		switch (gameObject.name) {
		case "slimemessage":
			break;
		case "slimepoint":
			gameObject.GetComponent<TextMesh> ().text = ""+gamemanager.GetComponent<GameManager> ().Slimepoint;
			break;
		}
	}

	void Update () {
		if (Input.touchCount > 0) {
			if (Input.touchCount == 1) { //터치가 하나일 때
				if (Input.GetTouch (0).phase == TouchPhase.Began) { //처음 눌린 상태인지 검사
					Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
					Vector2 touchpos = new Vector2 (pos.x, pos.y);
					if (GetComponent<Collider2D> () == Physics2D.OverlapPoint (touchpos)) {
						if (gameObject.name == "ad")
							Ads ();
						else
							Touched (); //터치확인
					}
				}
			} 
		}
	}

	void Touched()
	{
		Destroy (gameObject);
	}

	void Ads()
	{
		GameObject.Find ("UnityAdsManager").GetComponent<UnityAdsHelper> ().ShowRewardedAd ();
	}
}
