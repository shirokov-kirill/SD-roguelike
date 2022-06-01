package model.entity.attributes

import model.entity.facets.Cloneable

/*
This is a base interface for any attribute
(empty now)
 */

interface Attribute {
    fun clone(): Attribute
}