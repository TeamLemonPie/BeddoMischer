package de.lemonpie.beddomischer.settings

/**
  * Util class, that handles the current de.lemonpie.beddomischer.settings saver and loader.
  *
  * @author tobias
  */
object SettingsHandler {

	/**
	  * Get the current loader.
	  *
	  * @return loader.
	  */
	def loader = new PropertiesSettingsHandler()

	/**
	  * Get the current saver.
	  *
	  * @return saver.
	  */
	def saver = new PropertiesSettingsHandler()

}
