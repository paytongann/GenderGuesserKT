package payton.gann.genderguesser.model

data class GenderData (
        var name: String,
        var gender: String,
        var probability: Float,
        var count: Int
)