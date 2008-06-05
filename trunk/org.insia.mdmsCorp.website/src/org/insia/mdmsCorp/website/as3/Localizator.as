package org.insia.mdmsCorp.website.as3
{
    import flash.events.Event;
    
    import mx.resources.ResourceBundle;
    import mx.resources.ResourceManager; 
    
    public class Localizator
    {
        public static const FRENCH:int = 1;
        public static const ENGLISH:int = 2; 
        protected static var myLocalizator:Localizator;

        [Bindable]
        private var cur:ResourceBundle;

        [ResourceBundle("en_US")]
        private var rb_eng:ResourceBundle;
        [ResourceBundle("fr_FR")]
        private var rb_fra:ResourceBundle;

        private var _lang:int;

        public function Localizator (language:int=ENGLISH)
        {
            selectLang( language );
        }

        private function selectLang( language:int ) : void
        {
            this._lang = language;
            switch ( language ) 
            {
            case ENGLISH :
                this.cur = rb_eng;
                break;
            case FRENCH :
                this.cur = rb_fra;
                break;
            default :
                this._lang = Localizator.ENGLISH;
                this.cur = rb_eng;
                break;
            }
        }
         public static function getInstance (language:int) : Localizator 
         {
            if( Localizator.myLocalizator == null )
            {
                Localizator.myLocalizator = new Localizator(language);
            }
            return Localizator.myLocalizator;
        }

        [Bindable(event="langChange")]
        public function getText( key:String ) : String 
        {
        	//return ResourceManager.getInstance().getString(this.cur, key);
            return this.cur.getString( key );
        }

        [Bindable(event="langChange")]
        public function gT( key:String ) : String 
        {
            return this.getText(key);
        }

        public function getLang () : int 
        {
            return this._lang;
        }

        public function setLang ( language:int ) : void 
        {
            if( this._lang != language )
            {
                this.selectLang( language );
                var e:Event = new Event("langChange");
                this.dispatchEvent(e);
            }
        }
    }
}