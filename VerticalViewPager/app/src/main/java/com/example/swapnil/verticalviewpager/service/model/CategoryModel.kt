package com.example.swapnil.verticalviewpager.service.model

class CategoryModel {
    var categoryName: String
    var categoryImage: Int

    constructor(categoryName: String = "", categoryImage: Int) {
        this.categoryName = categoryName
        this.categoryImage = categoryImage
    }
}