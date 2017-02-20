package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (this.year != other.year) return this.year - other.year
        if (this.month != other.month) return this.month - other.month
        return this.dayOfMonth - other.dayOfMonth
    }

    operator fun rangeTo(d: MyDate): DateRange =
            DateRange(start = this, endInclusive = d)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange =
    DateRange(start = this, endInclusive = other)


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(d: MyDate): Boolean = (start < d) && (d <= endInclusive)
}
