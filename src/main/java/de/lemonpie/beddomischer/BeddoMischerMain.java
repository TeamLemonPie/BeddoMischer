package de.lemonpie.beddomischer;

import static spark.Spark.get;
import static spark.Spark.port;

public class BeddoMischerMain {
	public static void main(String[] args) {
		port(9999);

		get("/", (req, res) -> "Hello World");
	}
}
