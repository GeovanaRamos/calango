package tcccalango.view.interfaces;

import tcccalango.util.settings.CalangoSettings;

public interface ITCCCalangoSettingsSuject {
   void adicionarObservador(ITCCCalangoViewObserver var1);

   void removerObservador(ITCCCalangoViewObserver var1);

   void notificarObservadores(CalangoSettings var1);
}
