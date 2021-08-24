package pl.students.szczepieniaapp.network.mapper

import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.domain.util.DomainMapper
import pl.students.szczepieniaapp.network.model.route.Route

class MyRouteMapper : DomainMapper<Route, MyRoute> {

    override fun mapToDomainModel(entity: Route): MyRoute {
        return MyRoute(
            distance = entity.legs?.get(0)?.distance?.text,
            duration = entity.legs?.get(0)?.duration?.text,
            endAddress = entity.legs?.get(0)?.endAddress,
            startAddress = entity.legs?.get(0)?.startAddress,
            points = entity.overviewPolyline?.points
        )
    }

    override fun mapToDomainModelList(initial: List<Route>): List<MyRoute> {
        return initial.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainModel(domainModel: MyRoute): Route {
        TODO("Not yet implemented")
    }
}