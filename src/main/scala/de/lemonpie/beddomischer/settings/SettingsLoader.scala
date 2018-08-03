package de.lemonpie.beddomischer.settings

import java.io.IOException
import java.nio.file.Path

/**
  * Handles a de.lemonpie.beddomischer.settings object, to deserialize it.
  *
  * @author tobias
  */
trait SettingsLoader {

  /**
    * Load a file and create a de.lemonpie.beddomischer.settings object.
    *
    * @param path path of de.lemonpie.beddomischer.settings file.
    * @throws IOException file could not be read
    * @return de.lemonpie.beddomischer.settings object.
    */
  @throws[IOException]
  def load(path: Path): Settings
}
