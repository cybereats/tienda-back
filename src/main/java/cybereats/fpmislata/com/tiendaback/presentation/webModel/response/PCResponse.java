package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

public record PCResponse(
                Long id,
                String label,
                String slug,
                int runtime,
                String specs,
                String workingSince,
                String image,
                CategoryPCResponse categoryPCResponse) {
}
