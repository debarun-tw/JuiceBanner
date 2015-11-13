package com.example.roobab.juicebanner;

public class OrderItem {
        public String drinkName;
        public String employeeName;
        public String quantity;
        public int imageResId;

        public OrderItem(String drinkName, String employeeName, String quantity, int imageId) {
            this.drinkName = drinkName;
            this.employeeName = employeeName;
            this.quantity = quantity;
            this.imageResId = imageId;
        }

}
