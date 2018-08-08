package model.entity;

import model.Area;

public enum PowerUp {
	CHITARRA {

		@Override
		public Area getArea() {
			return new Area(0.04, 0.1);
		}

        @Override
        public String getImage() {
            return "pw1/chitarra.png";
        }
	},

	SIGARETTA {
		@Override
		public Area getArea() {
			return new Area(0.03, 0.03);
		}

        @Override
        public String getImage() {
            return "pw1/sigaretta.png";
        }
	},

	ZUCCHERO {
		@Override
		public Area getArea() {
			return new Area(0.02, 0.05);
		}

        @Override
        public String getImage() {
            return "pw1/stecca.png";
        }
	},

	PISTOLA {
		@Override
		public Area getArea() {
			return new Area(0.06, 0.04 );
		}

        @Override
        public String getImage() {
            return "pw1/gun.png";
        }
	};

	/**
	 * @return the PowerUp area
	 */
	public abstract Area getArea();
	
	/**
	 * @return powerUpImage
	 */
	public abstract String getImage();
}
