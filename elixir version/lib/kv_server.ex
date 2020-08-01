require Logger

defmodule KVServer do
  def accept(port) do
    # The options below mean:
    #
    # 1. `:binary` - receives data as binaries (instead of lists)
    # 2. `packet: :line` - receives data line by line
    # 3. `active: false` - blocks on `:gen_tcp.recv/2` until data is available
    # 4. `reuseaddr: true` - allows us to reuse the address if the listener crashes
    #
    {:ok, socket} = :gen_tcp.listen(port,
                      [:binary, packet: :line, active: false, reuseaddr: true])
    Logger.info "Accepting connections on port #{port}"
    loop_acceptor(socket)
  end

  defp loop_acceptor(socket) do
    {:ok, client} = :gen_tcp.accept(socket)
    serve(client)
    loop_acceptor(socket)
  end

  defp serve(socket) do
    # This extended code is the same as the write_line() code.
    # socket
    # |> read_line()
    # |> write_line(socket)

    # Remove EOL from client input
    line = read_line(socket) |> String.replace("\r", "") |> String.replace("\n", "")

    #write_line("[Server]: You just said" <> read_line(socket) |> String.string("\n\r"), socket)
    write_line("[Server]: You just said \"" <> line <> "\"\n", socket)

    serve(socket)
  end

  defp read_line(socket) do
    {:ok, data} = :gen_tcp.recv(socket, 0)
    data
  end

  defp write_line(line, socket) do
    :gen_tcp.send(socket, line)
  end
end