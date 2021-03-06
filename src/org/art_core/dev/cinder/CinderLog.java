package org.art_core.dev.cinder;

import org.art_core.dev.cinder.prefs.CinderPrefPage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * The logger of convenience for the Cinder plug-in.
 */
public final class CinderLog {
	public final int OK = IStatus.OK;
	public final int INFO = IStatus.INFO;
	public final int WARNING = IStatus.WARNING;
	public final int ERROR = IStatus.ERROR;
	
	private CinderLog() {}
	
	/**
	 * Logs debug messages if the config allows it.
	 * @param message
	 */
	public static void logDebug(final String message) {
		IPreferenceStore ipsPref = CinderPlugin.getDefault().getPreferenceStore();
		String sPrefKey = CinderPrefPage.P_BOOLEAN + "_show_debug";
		boolean bDebug = ipsPref.getBoolean(sPrefKey);
		if (bDebug) {
			logInfo(message);
		}
	}
	
	/**
	 * Logs a non-critical exception as information.
	 * @param message
	 * @param exception
	 */
	public static void logErrorInfo(final String message, final Throwable exception) {
		IPreferenceStore ipsPref = CinderPlugin.getDefault().getPreferenceStore();
		String sPrefKey = CinderPrefPage.P_BOOLEAN + "_show_debug";
		boolean bDebug = ipsPref.getBoolean(sPrefKey);
		if (bDebug) {
			log (IStatus.INFO, IStatus.OK, message, exception);
		}
	}
	
   /**
    * Log the specified information.
    * 
    * @param message, a human-readable message, localized to the
    *           current locale.
    */
	public static void logInfo(final String message) {
		log(IStatus.INFO, IStatus.OK, message, null);
	}

   /**
    * Log the specified error.
    * 
    * @param exception, a low-level exception.
    */
	public static void logError(final Throwable exception) {
		logError("Unexpected Exception", exception);
	}

   /**
    * Log the specified error.
    * 
    * @param message, a human-readable message, localized to the
    *           current locale.
    * @param exception, a low-level exception, or <code>null</code>
    *           if not applicable.
    */
	public static void logError(final String message, final Throwable exception) {
		log(IStatus.ERROR, IStatus.OK, message, exception);
	}

   /**
    * Log the specified information.
    * 
    * @param severity, the severity; one of the following:
    *           <code>IStatus.OK</code>,
    *           <code>IStatus.ERROR</code>,
    *           <code>IStatus.INFO</code>, or
    *           <code>IStatus.WARNING</code>.
    * @param pluginId. the unique identifier of the relevant
    *           plug-in.
    * @param code, the plug-in-specific status code, or
    *           <code>OK</code>.
    * @param message, a human-readable message, localized to the
    *           current locale.
    * @param exception, a low-level exception, or <code>null</code>
    *           if not applicable.
    */
	public static void log(final int severity, final int code, final String message,
			final Throwable exception) {
		log(createStatus(severity, code, message, exception));
	}

   /**
    * Create a status object representing the specified information.
    * 
    * @param severity, the severity; one of the following:
    *           <code>IStatus.OK</code>,
    *           <code>IStatus.ERROR</code>,
    *           <code>IStatus.INFO</code>, or
    *           <code>IStatus.WARNING</code>.
    * @param pluginId, the unique identifier of the relevant
    *           plug-in.
    * @param code, the plug-in-specific status code, or
    *           <code>OK</code>.
    * @param message, a human-readable message, localized to the
    *           current locale.
    * @param exception, a low-level exception, or <code>null</code>
    *           if not applicable.
    * @return, the status object (not <code>null</code>).
    */
   public static IStatus createStatus(final int severity, final int code, final String message,
			final Throwable exception) {
		return new Status(severity, CinderPlugin.PLUGIN_ID, code,
				message, exception);
	}

   /**
    * Log the given status.
    * 
    * @param status, the status to log.
    */
	public static void log(final IStatus status) {
		CinderPlugin.getDefault().getLog().log(status);
	}
}
