package com.example.roobab.juicebanner;

public class OrderItem {
        public String drinkName;
        public String employeeName;
        public String quantity;
        public int imageResId;
        public boolean isSugarless;

        public OrderItem(String drinkName, String employeeName, String quantity, int imageId, boolean isSugarless) {
            this.drinkName = drinkName;
            this.employeeName = employeeName;
            this.quantity = quantity;
            this.imageResId = imageId;
            this.isSugarless = isSugarless;
        }

}
