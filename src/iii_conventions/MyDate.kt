package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (this.year != other.year) return this.year - other.year
        if (this.month != other.month) return this.month - other.month
        return this.dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange =
    DateRange(start = this, endInclusive = other)

operator fun MyDate.plus(interval: TimeInterval): MyDate =
        this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate =
        this.addTimeIntervals(interval.timeInterval, interval.count)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(count: Int): RepeatedTimeInterval =
            RepeatedTimeInterval(timeInterval = this, count = count)
}
class RepeatedTimeInterval(val timeInterval: TimeInterval, val count: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object: Iterator<MyDate> {
            var current = start
            override fun hasNext(): Boolean {
                return current <= endInclusive
            }

            override fun next(): MyDate {
                val temp = current
                current = current.nextDay()
                return temp
            }
        }
    }

    operator fun contains(d: MyDate): Boolean = (start < d) && (d <= endInclusive)
}
