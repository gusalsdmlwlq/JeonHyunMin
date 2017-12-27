using UnityEngine;
using System.Collections;

public class Slime_beat : MonoBehaviour {

	float speed;

	void Start () {
		GameObject.Find("SoundManager").GetComponent<SoundManager>().Playsound("slime");
		speed = GameObject.Find ("Background_Game").GetComponent<Game> ().speed;
		StartCoroutine ("Drop");
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

	void OnDestroy() //터지는 이펙트
	{
		switch (gameObject.name) {
		case "red_beat(Clone)":
			Instantiate (Resources.Load ("Prefabs/red_pop"), transform.position, transform.rotation);
			break;
		case "blue_beat(Clone)":
			Instantiate (Resources.Load ("Prefabs/blue_pop"), transform.position, transform.rotation);
			break;
		case "yellow_beat(Clone)":
			Instantiate (Resources.Load ("Prefabs/yellow_pop"), transform.position, transform.rotation);
			break;
		case "green_beat(Clone)":
			Instantiate (Resources.Load ("Prefabs/green_pop"), transform.position, transform.rotation);
			break;
		default:
			Instantiate (Resources.Load ("Prefabs/double_pop"), transform.position, transform.rotation);
			break;
		}
	}
}
