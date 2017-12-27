using UnityEngine;
using System.Collections;

public class Line : MonoBehaviour {

	float speed;

	void Start () {
		speed = GameObject.Find ("Background_Game").GetComponent<Game> ().speed;
		if (GameObject.Find ("GameManager").GetComponent<GameManager> ().slimelist.Count > 0) {
			StartCoroutine ("Drop");
		}
		Destroy (gameObject, 0.2f);
	}

	void Update () {
	
	}

	IEnumerator Drop()
	{
		while(transform.localPosition.y >= -6.8f && !GameObject.Find("GameManager").GetComponent<GameManager>().pause)
		{
			gameObject.transform.Translate (new Vector3 (0, Time.deltaTime * speed, 0));
			yield return new WaitForEndOfFrame();
		}
	}
}
