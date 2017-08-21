package de.lemonpie.beddomischer.settings

import java.io.IOException
import java.nio.file.Path

/**
  * Handles a de.lemonpie.beddomischer.settings object, to serialize it.
  *
  * @author tobias
  */
trait SettingsSaver {

	/**
	  * Save the object to a given path.
	  *
	  * @param settings de.lemonpie.beddomischer.settings object.
	  * @param path     path to save the de.lemonpie.beddomischer.settings.
	  * @throws IOException File write operation failed.
	  */
	@throws[IOException]
	def save(settings: Settings, path: Path)

	/**
	  * Create a default de.lemonpie.beddomischer.settings file on disk.
	  *
	  * @param path path to save the de.lemonpie.beddomischer.settings.
	  * @throws IOException File write operation failed.
	  */
	@throws[IOException]
	def defaultSettings(path: Path): Unit = save(new Settings(), path)
}
