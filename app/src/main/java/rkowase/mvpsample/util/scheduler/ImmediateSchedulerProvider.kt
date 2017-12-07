package rkowase.mvpsample.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of the [BaseSchedulerProvider] making all [Scheduler]s execute
 * synchronously so we can easily run assertions in our tests.
 * <p>
 * To achieve this, we are using the [io.reactivex.internal.schedulers.TrampolineScheduler] from
 * the [Schedulers] class.
 */
class ImmediateSchedulerProvider : BaseSchedulerProvider {
    override fun computation(): Scheduler = Schedulers.trampoline()
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}