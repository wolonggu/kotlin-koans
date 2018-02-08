package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {


    override operator fun compareTo(other: MyDate): Int {
        if (this.year > other.year) return 1
        if (this.year < other.year) return -1
        if (this.month > other.month) return 1
        if (this.month < other.month) return -1
        if (this.dayOfMonth > other.dayOfMonth) return 1
        if (this.dayOfMonth < other.dayOfMonth) return -1
        return 0
    }

    operator fun plus(intervals: Intervals): MyDate = this.addTimeIntervals(intervals.interval, intervals.number)

    operator fun plus(intervals: TimeInterval): MyDate = this.addTimeIntervals(intervals, 1)



}

fun MyDate.plus(intervals: Intervals): MyDate {
    return this.addTimeIntervals(intervals.interval, intervals.number)
}


operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
    ;


    operator fun times(number: Int): Intervals = Intervals(this, number)
}

fun TimeInterval.times(number: Int): Intervals = Intervals(this, number)

data class Intervals(val interval: TimeInterval, val number: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {

            var current: MyDate = start;
            override fun next(): MyDate {
                current = current.nextDay()
                return current
            }

            override fun hasNext(): Boolean {
                return current.nextDay() <= endInclusive
            }

        }
    }

    operator  fun contains(d: MyDate): Boolean {
        return d >= start && d <= endInclusive
    }


}
