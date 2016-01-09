package com.example.roobab.juicebanner;

public class OrderItem {
        public String drinkName;
        public String employeeName;
        public String quantity;
        public int imageResId;
        public boolean isSugarless;
        public String _id;
        public  boolean isFruit;

    public OrderItem(String drinkName, String employeeName, String quantity,
                     int imageId, boolean isSugarless,  String _id,  boolean isFruit) {
            this.drinkName = drinkName;
            this.employeeName = employeeName;
            this.quantity = quantity;
            this.imageResId = imageId;
            this.isSugarless = isSugarless;
            this._id = _id;
            this.isFruit = isFruit;
        }

}
