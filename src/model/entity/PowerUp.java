package model.entity;

import model.Area;

public enum PowerUp {
	CHITARRA {

		@Override
		public Area getArea() {
			return new Area(0.1, 0.1);
		}
	},

	SIGARETTA {
		@Override
		public Area getArea() {
			return new Area(0.1, 0.1);
		}
	},

	ZUCCHERO {
		@Override
		public Area getArea() {
			return new Area(0.1, 0.1);
		}
	},

	PISTOLA {
		@Override
		public Area getArea() {
			return new Area(0.1, 0.1);
		}
	};

	/**
	 * @return the PowerUp area
	 */
	public abstract Area getArea();
}
