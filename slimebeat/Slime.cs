using UnityEngine;
using System.Collections;

public class Slime : MonoBehaviour {

	GameObject gamemanager;
	float speed; //슬라임이 내려오는 속도
	public bool isbeat=false;
	public bool doubleslime=false;
	public bool isdouble=false;

	void Start () {
		gamemanager = GameObject.Find ("GameManager");
		gamemanager.GetComponent<GameManager> ().slimelist.Add (gameObject); //슬라임 리스트에 추가
		speed = GameObject.Find ("Background_Game").GetComponent<Game> ().speed;
		if (isdouble == true) {
			doubleslime = true;
			switch (Random.Range (0, 6)) {
			case 0: //rb
				gameObject.name = "rb(Clone)";
				gameObject.GetComponent<SpriteRenderer>().sprite = (Sprite)Resources.Load("Images/rb",typeof(Sprite));
				break;
			case 1: //rg
				gameObject.name = "rg(Clone)";
				gameObject.GetComponent<SpriteRenderer>().sprite = (Sprite)Resources.Load("Images/rg",typeof(Sprite));
				break;
			case 2: //ry
				gameObject.name = "ry(Clone)";
				gameObject.GetComponent<SpriteRenderer>().sprite = (Sprite)Resources.Load("Images/ry",typeof(Sprite));
				break;
			case 3: //bg
				gameObject.name = "bg(Clone)";
				gameObject.GetComponent<SpriteRenderer>().sprite = (Sprite)Resources.Load("Images/bg",typeof(Sprite));
				break;
			case 4: //by
				gameObject.name = "by(Clone)";
				gameObject.GetComponent<SpriteRenderer>().sprite = (Sprite)Resources.Load("Images/by",typeof(Sprite));
				break;
			case 5: //gy
				gameObject.name = "gy(Clone)";
				gameObject.GetComponent<SpriteRenderer>().sprite = (Sprite)Resources.Load("Images/gy",typeof(Sprite));
				break;
			}
		}
		StartCoroutine ("Drop");
		gamemanager.GetComponent<GameManager> ().slimetest++;
	}

	void Update () {
	
	}

	void OnDestroy()
	{
		gamemanager.GetComponent<GameManager> ().slimelist.Remove (gameObject); //슬라임 리스트에서 제거
		if (isbeat) { //비트시켜 터뜨렸을 경우 이펙트로 이어짐
			switch (gameObject.name) {
			case "r(Clone)":
				Instantiate (Resources.Load ("Prefabs/red_beat"), transform.position, transform.rotation);
				break;
			case "b(Clone)":
				Instantiate (Resources.Load ("Prefabs/blue_beat"), transform.position, transform.rotation);
				break;
			case "g(Clone)":
				Instantiate (Resources.Load ("Prefabs/green_beat"), transform.position, transform.rotation);
				break;
			case "y(Clone)":
				Instantiate (Resources.Load ("Prefabs/yellow_beat"), transform.position, transform.rotation);
				break;
			case "rb(Clone)":
				Instantiate (Resources.Load ("Prefabs/rb_beat"), transform.position, transform.rotation);
				break;
			case "rg(Clone)":
				Instantiate (Resources.Load ("Prefabs/rg_beat"), transform.position, transform.rotation);
				break;
			case "ry(Clone)":
				Instantiate (Resources.Load ("Prefabs/ry_beat"), transform.position, transform.rotation);
				break;
			case "bg(Clone)":
				Instantiate (Resources.Load ("Prefabs/bg_beat"), transform.position, transform.rotation);
				break;
			case "by(Clone)":
				Instantiate (Resources.Load ("Prefabs/by_beat"), transform.position, transform.rotation);
				break;
			case "gy(Clone)":
				Instantiate (Resources.Load ("Prefabs/gy_beat"), transform.position, transform.rotation);
				break;
			}
		}
	}

	IEnumerator Drop() //슬라임 내려오는 코루틴
	{
		while (transform.localPosition.y >= -6.8f && gamemanager.GetComponent<GameManager>().isresult == false) {
			if (!gamemanager.GetComponent<GameManager> ().pause) {
				gameObject.transform.Translate (new Vector3 (0, Time.deltaTime * speed, 0));
			}
			yield return new WaitForEndOfFrame ();
		}
		Destroy (gameObject);
		if (GameObject.Find ("Background_Game").GetComponent<Game> ().hp > 0) {
			GameObject.Find ("Background_Game").GetComponent<Game> ().hp -= 10;
			GameObject.Find ("Background_Game").GetComponent<Game> ().Combo = 0;
			Instantiate (Resources.Load ("Prefabs/damage"), new Vector3 (0, 7.2f, -1), Quaternion.Euler (0, 90, 0));
		}
	}
}
